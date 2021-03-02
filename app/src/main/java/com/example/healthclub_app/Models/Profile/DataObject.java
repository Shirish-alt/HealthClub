package com.example.healthclub_app.Models.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataObject {

    @SerializedName("user")
    UserObject userObject;

    @SerializedName("user_data")
    UserData userData;

    @SerializedName("progress")
    ArrayList<Progress> progressArrayList;


    @SerializedName("first")
    First first;

    @SerializedName("last")
    Last last;

    @SerializedName("overview")
    Overview overview;

    public DataObject(UserObject userObject, UserData userData, ArrayList<Progress> progressArrayList, First first, Last last, Overview overview) {
        this.userObject = userObject;
        this.userData = userData;
        this.progressArrayList = progressArrayList;
        this.first = first;
        this.last = last;
        this.overview = overview;
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public ArrayList<Progress> getProgressArrayList() {
        return progressArrayList;
    }

    public void setProgressArrayList(ArrayList<Progress> progressArrayList) {
        this.progressArrayList = progressArrayList;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Last getLast() {
        return last;
    }

    public void setLast(Last last) {
        this.last = last;
    }

    public Overview getOverview() {
        return overview;
    }

    public void setOverview(Overview overview) {
        this.overview = overview;
    }
}
