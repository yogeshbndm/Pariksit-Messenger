package com.pariksit.pariksitmessenger.ChatFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pariksit.pariksitmessenger.R;
import com.pariksit.pariksitmessenger.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pariksit on 3/1/2019.
 */

public class ChatFragment extends Fragment implements View.OnClickListener {
    final String TAG = "ChatFragment";
    String registeredBy, userId2;

    SharedPreferences.Editor prefsEditor;
    SharedPreferences prefs;
    View view;
    FloatingActionButton refresh;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.e1_fragment_chat_main, container, false);

        refresh = view.findViewById(R.id.chat_refresh);
        refresh.setOnClickListener(this);

        prefs = getActivity().getSharedPreferences(Utils.PREFS_NAME, 0);
        prefsEditor = prefs.edit();
        registeredBy = prefs.getString("registeredby", "DEFAULT");
        userId2 = prefs.getString("uid", "false");
        final String userId = userId2;

        //now list of users he can chat with
        if(prefs.contains("chatNodes")) {
            populateChatListView();
        }
        else {
            fetchChatlist(userId, registeredBy);
        }
        return view;
    }

    private void fetchChatlist(String userId, String registeredBy) {
        AndroidNetworking.post(Utils.chatlisturl)
                .addBodyParameter("uid", userId)
                .addBodyParameter("registeredby", registeredBy)
                .setTag("fetchChatList")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(User.class, new ParsedRequestListener<List<User>>() {
                    @Override
                    public void onResponse(List<User> userList) {
                        Gson gson = new Gson();
                        String json = gson.toJson(userList);
                        Log.e(TAG, "Chat Nodes recieved from server");
                        prefsEditor.putString("chatNodes", json);
                        prefsEditor.commit();
                        populateChatListView();
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e(TAG, error.toString());
                        // handle error
                    }
                });
    }

    private void populateChatListView() {
        refresh.clearAnimation();
        Gson gson = new Gson();
        String json = prefs.getString("partialChatNodes", "");
        Type type = new TypeToken<List<User>>() {}.getType();
        List<User> partialChatNodesFromServer = gson.fromJson(json, type);

        List<ChatNode> chatNodeList = new ArrayList<>();
        ChatListAdapter chatListAdapter = new ChatListAdapter(chatNodeList, getActivity());

        RecyclerView chatRV = view.findViewById(R.id.listviewChat);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());

        chatRV.setLayoutManager(layoutManager1);
        chatRV.setItemAnimator(new DefaultItemAnimator());
        chatRV.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        chatRV.setAdapter(chatListAdapter);

        for(int i=0; i<partialChatNodesFromServer.size(); i++) {
            User pChatNode = partialChatNodesFromServer.get(i);
            ChatNode chatNode = new ChatNode(pChatNode.getId(), pChatNode.getName(),
                    "Tap to start chat.", "", "",
                    pChatNode.getDp(), pChatNode.getDpStamp(),pChatNode.getUserType());
            chatNodeList.add(chatNode);
        }

        chatListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        AndroidNetworking.forceCancel("fetchChatList");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_refresh:
                fetchChatlist(userId2, registeredBy);
                Animation animation = new RotateAnimation(0.0f, 360.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatCount(5);
                animation.setDuration(2000);
                view.startAnimation(animation);
            break;
        }
    }
}
