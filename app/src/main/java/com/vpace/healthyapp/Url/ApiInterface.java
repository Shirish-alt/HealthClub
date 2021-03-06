package com.vpace.healthyapp.Url;

import com.vpace.healthyapp.Models.Profile.DataObject;
import com.vpace.healthyapp.Models.SuggetionData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("me")
    Call<DataObject> getProfileData(@Header("Authorization") String auth_token);

    @POST("suggestion")
    Call<SuggetionData> Suggestion(SuggetionData suggetionData);
}
