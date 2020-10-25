package com.pariksit.pariksitmessenger.ChatFragment.Messages;

public class MessagesTemplate {
    public String chatId, messageId, senderId, message="", messageType, mediaUrl="", mediaThumbUrl="";
    public Long timeStamp;

    public MessagesTemplate() {

    }

    public MessagesTemplate(String chatId, String messageId, String senderId, String message, String messageType, String mediaUrl, String mediaThumbUrl, Long timeStamp) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.senderId = senderId;
        this.message = message;
        this.messageType = messageType;
        this.mediaUrl = mediaUrl;
        this.mediaThumbUrl = mediaThumbUrl;
        this.timeStamp = timeStamp;
    }

    public String getChatId() {
        return chatId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getMediaThumbUrl() {
        return mediaThumbUrl;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
