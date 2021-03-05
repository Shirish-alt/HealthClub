package com.vpace.healthyapp.Url;

import com.vpace.healthyapp.Models.Profile.DataObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {

    @GET("me")
    Call<DataObject> getProfileData(@Header("Authorization") String auth_token);
}
