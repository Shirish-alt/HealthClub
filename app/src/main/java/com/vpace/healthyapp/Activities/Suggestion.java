package com.vpace.healthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vpace.healthyapp.MainActivity;
import com.vpace.healthyapp.Models.SuggetionData;
import com.vpace.healthyapp.R;
import com.vpace.healthyapp.SessionManagment;
import com.vpace.healthyapp.Url.ApiInterface;
import com.vpace.healthyapp.Url.NetworkInstance;
import com.vpace.healthyapp.Urls;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Suggestion extends AppCompatActivity {

    EditText suggestion;
    TextView title;
    String s_suggetion,username,token;
    Button btn_submit;
    RelativeLayout rel1,rel2;


    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        suggestion=findViewById(R.id.suggetion_box);
        title=findViewById(R.id.suggestion_title);
        btn_submit=findViewById(R.id.submit_btn);
        rel1=findViewById(R.id.rel1);
        rel2=findViewById(R.id.rel2);

        apiInterface= NetworkInstance.getInstance().create(ApiInterface.class);

        SessionManagment  sessionManagment=new SessionManagment(Suggestion.this);
        HashMap<String,String> map=sessionManagment.getSession();
        username=map.get(SessionManagment.KEY_NAME);
        token=map.get(SessionManagment.KEY_TOKEN);

      if(token.equals(null)){
          rel1.setVisibility(View.GONE);
          rel2.setVisibility(View.VISIBLE);

      }else {
          rel1.setVisibility(View.VISIBLE);
          rel2.setVisibility(View.GONE);

      }




        if(username.equals(null)){
            title.setText(" ");

        }else {
            title.setText(username);
        }


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_suggetion=suggestion.getText().toString().trim();
                if(TextUtils.isEmpty(s_suggetion)) {
                        Toast.makeText(Suggestion.this, "Field is Required", Toast.LENGTH_SHORT).show();
                        suggestion.setError("This Field is Required");
                        return;
                    }else {
                        suggestion.setText(" ");
                        SendData(token);
                    }



            }
        });


    }

    private void SendData(String token) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.suggestion_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Suggestion.this, "Thank for your Suggetion", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Suggestion.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Suggestion.this, "Err:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header=new HashMap<>();
                header.put("Authorization","Bearer "+token);
                return  header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("message",s_suggetion);
                return  map;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(Suggestion.this);
        queue.add(stringRequest);
    }
}