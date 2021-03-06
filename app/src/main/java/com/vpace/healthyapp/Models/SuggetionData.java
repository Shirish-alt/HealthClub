package com.vpace.healthyapp.Models;

public class SuggetionData {

    String auth_token;
    String text;

    public SuggetionData(String auth_token, String text) {
        this.auth_token = auth_token;
        this.text = text;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
