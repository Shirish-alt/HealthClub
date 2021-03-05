package com.vpace.healthyapp.Models.Profile;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("user_id")
    int user_id;

    @SerializedName("gender")
    String Gender;
    @SerializedName("address")
   String Address;
    @SerializedName("city")
   String city;
    @SerializedName("state")
    String state;

    @SerializedName("dob")
    String dob;

    public UserData(int user_id, String gender, String address, String city, String state, String dob) {
        this.user_id = user_id;
        Gender = gender;
        Address = address;
        this.city = city;
        this.state = state;
        this.dob = dob;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
