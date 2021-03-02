package com.example.healthclub_app.Models.Profile;

import com.google.gson.annotations.SerializedName;

public class Roles {

    @SerializedName("name")
    String role_name;
    @SerializedName("guard_name")
    String guard_name;

    public Roles(String role_name, String guard_name) {
        this.role_name = role_name;
        this.guard_name = guard_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getGuard_name() {
        return guard_name;
    }

    public void setGuard_name(String guard_name) {
        this.guard_name = guard_name;
    }
}
