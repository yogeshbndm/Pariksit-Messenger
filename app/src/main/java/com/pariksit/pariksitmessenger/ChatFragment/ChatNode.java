package com.pariksit.pariksitmessenger.ChatFragment;

public class ChatNode {
    String name, lastchat, lastchattime, chatunread, dp, chattype, id, dpstamp;

    public ChatNode() {
    }

    public ChatNode(String id, String name, String lastchat, String lastchattime, String chatunread, String dp, String dpstamp, String chattype) {
        this.id = id;
        this.name = name;
        this.lastchat = lastchat;
        this.lastchattime = lastchattime;
        this.chatunread = chatunread;
        this.dp = dp;
        this.chattype = chattype;
        this.dpstamp = dpstamp;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastchat() {
        return lastchat;
    }

    public void setLastchat(String lastchat) {
        this.lastchat = lastchat;
    }

    public String getLastchattime() {
        return lastchattime;
    }

    public void setLastchattime(String lastchattime) {
        this.lastchattime = lastchattime;
    }

    public String getChatunread() {
        return chatunread;
    }

    public void setChatunread(String chatunread) {
        this.chatunread = chatunread;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }


    public String getChattype() {
        return chattype;
    }

    public void setChattype(String chattype) {
        this.chattype = chattype;
    }

    public String getDpstamp() {
        return dpstamp;
    }

    public void setDpstamp(String dpstamp) {
        this.dpstamp = dpstamp;
    }
}
