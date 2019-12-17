package com.example.hostel.models;

public class Notifications {

    public static final String NOTIFY_ID = "notifyId";
    public static final String NOTIFY_MSG = "notifyMsg";

    private String notifyId;
    private String notifyMsg;
    private String notifyUserId;
    private String propertyId;
    private String notifyUserAvatar;

    public Notifications() {}

    public String _getNotifyId() {
        return notifyId;
    }

    public void _setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getNotifyUserId() {
        return notifyUserId;
    }

    public void setNotifyUserId(String notifyUserId) {
        this.notifyUserId = notifyUserId;
    }

    public String _getNotifyUserAvatar() {
        return notifyUserAvatar;
    }

    public void _setNotifyUserAvatar(String notifyUserAvatar) {
        this.notifyUserAvatar = notifyUserAvatar;
    }
}
