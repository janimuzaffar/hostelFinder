package com.example.hostel.models;

public class Notifications {

    private String notifyId;
    private String userId;
    private String notifyUsername;
    private String notifyMsg;

    public Notifications() {}

    public String _getNotifyId() {
        return notifyId;
    }

    public void _setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotifyUsername() {
        return notifyUsername;
    }

    public void setNotifyUsername(String notifyUsername) {
        this.notifyUsername = notifyUsername;
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }
}
