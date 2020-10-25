package com.pariksit.pariksitmessenger.ChatFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pariksit.pariksitmessenger.R;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> implements View.OnClickListener {

    private List<ChatNode> chatNodes;
    Activity activity;

    @Override
    public void onClick(View view) {
        TextView tv = view.findViewById(R.id.lastChat_Name);
        TextView tv2 = view.findViewById(R.id.chatWithId);
        ImageView imageView = view.findViewById(R.id.chattype);
        String lastChatName = tv.getText().toString();
        String lastChatId = tv2.getText().toString();
        String chattype = imageView.getTag().toString();


        Context context = view.getContext();
        Intent intent = new Intent(context, MainChatScreen.class);
        intent.putExtra("type", chattype);
        intent.putExtra("chatWith", lastChatId);
        intent.putExtra("chatWithName", lastChatName);
        context.startActivity(intent);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, lastchat, lastchattime, chatunread, chatWithId;
        ImageView dp, chattype;
        LinearLayout wrapperUnreadMessage, mainChatListNode;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lastChat_Name);
            lastchat = itemView.findViewById(R.id.lastChat_Message);
            lastchattime = itemView.findViewById(R.id.lastChat_MessageDate);
            chatunread = itemView.findViewById(R.id.chat_unread);
            dp = itemView.findViewById(R.id.dp);
            chattype = itemView.findViewById(R.id.chattype);
            wrapperUnreadMessage = itemView.findViewById(R.id.wrapper_unread_message);
            mainChatListNode = itemView.findViewById(R.id.mainChatListNode);
            chatWithId = itemView.findViewById(R.id.chatWithId);

        }
    }

    ChatListAdapter(List<ChatNode> chatNodes, Activity activity) {
        this.chatNodes = chatNodes;
        this.activity = activity;
    }

    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.e2_fragment_chat_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatListAdapter.MyViewHolder holder, int position) {
        ChatNode chatNode = chatNodes.get(position);
        holder.name.setText(chatNode.getName());
        holder.lastchat.setText(chatNode.getLastchat());
        holder.lastchattime.setText(chatNode.getLastchattime());
        holder.chatunread.setText(chatNode.getChatunread());
        holder.chatWithId.setText(chatNode.getId());

        Integer chatunreadint = 0;
        try {
            chatunreadint = Integer.parseInt(chatNode.getChatunread());
        } catch (NumberFormatException e) {

        }
        if(chatunreadint.equals(0)) {
            holder.wrapperUnreadMessage.setVisibility(View.GONE);
        }

        String type = chatNode.getChattype();
        if(type.equals("channel")) {
            holder.chattype.setBackgroundResource(R.drawable.channel);
            holder.chattype.setTag("channel");
        }
        else if(type.equals("teacher")) {
            holder.chattype.setBackgroundResource(R.drawable.teacher);
            holder.chattype.setTag("teacher");
        }
        else if(type.equals("personal")) {
            holder.chattype.setBackgroundResource(R.drawable.personal);
            holder.chattype.setTag("personal");
        }

        String stamp = chatNode.getDpstamp();

        Glide.with(activity)
                .load(chatNode.getDp()+"?stamp="+stamp)
                .centerCrop()
                .placeholder(R.drawable.dp_placeholder)
                .error(R.drawable.dp_placeholder)
                .into(holder.dp);

        holder.mainChatListNode.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return chatNodes.size();
    }
}
