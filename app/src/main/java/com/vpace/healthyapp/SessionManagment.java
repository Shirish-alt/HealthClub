package com.vpace.healthyapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.vpace.healthyapp.Models.User;

import java.util.HashMap;

public class SessionManagment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    public static String KEY_TOKEN="access_token";
    public static String KEY_USERNAME="username";
    public static String KEY_NAME="name";
    public static String USER_ID="id";


    public SessionManagment(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String token,String username,String name){
//        //save session of user whenever user is logged in
//        KEY_TOKEN = user.getAccess_token();
//        KEY_USERNAME=user.getUsername();
//
//        editor.putString(KEY_TOKEN,user.getAccess_token());
//        editor.putString(KEY_USERNAME,user.getUsername());

//        editor.commit();
        editor.putString(KEY_TOKEN,token);
        editor.putString(KEY_USERNAME,username);

        editor.putString(KEY_NAME,name);
        editor.commit();
    }

    public HashMap<String,String> getSession(){
//        //return user id whose session is saved
//        return sharedPreferences.getString(KEY_TOKEN, null);
        HashMap<String,String> map=new HashMap<>();
        map.put(KEY_TOKEN,sharedPreferences.getString(KEY_TOKEN,null));
        map.put(KEY_USERNAME,sharedPreferences.getString(KEY_USERNAME,null));
        map.put(KEY_NAME,sharedPreferences.getString(KEY_NAME,null));
        return  map;

    }

    public void removeSession(){
        editor.putString(KEY_TOKEN,null).commit();

    }


}