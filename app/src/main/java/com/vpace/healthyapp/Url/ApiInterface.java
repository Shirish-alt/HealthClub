package com.vpace.healthyapp.Url;

import com.vpace.healthyapp.Models.Profile.DataObject;
import com.vpace.healthyapp.Models.SuggetionData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("me")
    Call<DataObject> getProfileData(@Header("Authorization") String auth_token);

    @FormUrlEncoded
    @POST("suggestion")
    Call<ResponseBody> Sueggestion(
            @Header("Authorization") String auth_token,
            @Field("message") String suggestion
    );
}
