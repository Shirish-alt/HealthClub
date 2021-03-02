package com.example.healthclub_app.Models.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserObject {

    @SerializedName("name")
    String username;

    @SerializedName("email")
    String useremail;

    @SerializedName("phone")
    String usermoile;

    @SerializedName("profile_photo_url")
    String image_url;

    @SerializedName("roles")
    ArrayList<Roles> rolesArrayList;

    public UserObject(String username, String useremail, String usermoile, String image_url, ArrayList<Roles> rolesArrayList) {
        this.username = username;
        this.useremail = useremail;
        this.usermoile = usermoile;
        this.image_url = image_url;
        this.rolesArrayList = rolesArrayList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsermoile() {
        return usermoile;
    }

    public void setUsermoile(String usermoile) {
        this.usermoile = usermoile;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public ArrayList<Roles> getRolesArrayList() {
        return rolesArrayList;
    }

    public void setRolesArrayList(ArrayList<Roles> rolesArrayList) {
        this.rolesArrayList = rolesArrayList;
    }
}
