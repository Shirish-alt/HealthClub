package com.example.healthclub_app;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healthclub_app.Models.User;

public class SessionManagment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagment(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        //save session of user whenever user is logged in
        String access_token = user.getAccess_token();

        editor.putString(SESSION_KEY,access_token).commit();
    }

    public String getSession(){
        //return user id whose session is saved
        return sharedPreferences.getString(SESSION_KEY, null);
    }

    public void removeSession(){
        editor.putString(SESSION_KEY,null).commit();

    }
}