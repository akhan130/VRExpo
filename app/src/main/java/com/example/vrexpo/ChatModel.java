package com.example.vrexpo;

import java.security.Timestamp;
import java.util.Date;

public class ChatModel {
    private String message;
    private String senderId;
    private long timestamp;

    public ChatModel() {

    }

    public ChatModel(String message, String senderId, long timestamp) {
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getSenderId() {

        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getTimestampAsDate() {
        return new Date(timestamp);
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
