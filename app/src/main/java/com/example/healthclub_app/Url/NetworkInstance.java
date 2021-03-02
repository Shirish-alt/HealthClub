package com.example.healthclub_app.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkInstance {

    private static  final String BASE_URL="http://healthylifestylesclub.co.in/jet/api/";
    private static Retrofit retrofit=null;

    public static Retrofit getInstance(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }

}
