package com.example.healthclub_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthclub_app.R;
import com.example.healthclub_app.Urls;

import java.util.HashMap;
import java.util.Map;


public class CurrentDiet extends Fragment {

String access_token="";
WebView webView;
    public CurrentDiet(String token) {
        access_token=token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current_diet, container, false);

        webView=view.findViewById(R.id.diet_web_view);

        if(access_token==null){
            Toast.makeText(getActivity(), "Token Not Getting", Toast.LENGTH_LONG).show();
        }else{
            CurrentDietData(access_token);
        }



        return  view;
    }

    private void CurrentDietData(String access_token) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, Urls.Diet_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Diet-Res:",response);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Err:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer "+access_token);

                return  header;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }
}