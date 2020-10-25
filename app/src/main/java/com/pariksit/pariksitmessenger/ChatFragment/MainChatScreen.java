package com.pariksit.pariksitmessenger.ChatFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pariksit.pariksitmessenger.R;
import com.pariksit.pariksitmessenger.Utils;

public class MainChatScreen extends AppCompatActivity {

    String registeredBy, userId;
    SharedPreferences.Editor prefsEditor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e3_fragment_chat);

        prefs = this.getSharedPreferences(Utils.PREFS_NAME, 0);
        prefsEditor = prefs.edit();
        registeredBy = prefs.getString("registeredby", "DEFAULT");
        userId = prefs.getString("uid", "false");

        String node;
        String nodeType = getIntent().getStringExtra("type");
        String nodeChatWith = getIntent().getStringExtra("chatWith");
        String nodeChatWithName = getIntent().getStringExtra("chatWithName");

        Toast.makeText(this, nodeType+"\n"+nodeChatWith+"\n"+nodeChatWithName, Toast.LENGTH_SHORT).show();

        if(nodeType.equals("channel")) {
            node = nodeChatWith;
        }
        else {
            if(userId.compareToIgnoreCase(nodeChatWith)<0) {
                node = userId+"_"+nodeChatWith;
            }
            else {
                node = nodeChatWith+"_"+userId;
            }
        }

    }
}
