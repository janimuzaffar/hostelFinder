package com.example.hostel.models;

import com.example.hostel.helpers.TableNames;

import java.util.List;

public class Users {

    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String USER_AVATAR = "userAvatar";
    public static final String PROPERTY_REQUEST_SENT = "propertyRequestSent";

    private String userId;
    private String user_name;
    private String email;
    private String password;
    private String userAvatar;
    private List<String> propertyRequestSent;

    public Users() {}

    public Users(String username, String email, String pass, String uavatar, List<String> propRequestSent){
        this.user_name = username;
        this.email = email;
        this.password = pass;
        this.userAvatar = uavatar;
        this.propertyRequestSent = propRequestSent;
    }

    public String _getUserId() {
        return userId;
    }

    public void _setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<String> getPropertyRequestSent() {
        return propertyRequestSent;
    }

    public void setPropertyRequestSent(List<String> propertyRequestSent) {
        this.propertyRequestSent = propertyRequestSent;
    }
}
