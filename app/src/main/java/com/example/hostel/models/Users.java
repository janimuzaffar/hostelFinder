package com.example.hostel.models;

import com.example.hostel.helpers.TableNames;

public class Users {

    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String USER_AVATAR = "userAvatar";

    private String userId;
    private String user_name;
    private String email;
    private String password;
    private String userAvatar;

    public Users() {}

    public Users(String username, String email, String pass){
        this.user_name = username;
        this.email = email;
        this.password = pass;
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
}
