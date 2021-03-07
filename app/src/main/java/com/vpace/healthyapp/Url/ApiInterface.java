package com.vpace.healthyapp.Url;

import com.vpace.healthyapp.Models.Profile.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {

    @GET("me")
    Call<Profile> getProfileData(@Header("Authorization") String auth_token);
}
