package com.vpace.healthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vpace.healthyapp.MainActivity;
import com.vpace.healthyapp.Models.User;
import com.vpace.healthyapp.R;
import com.vpace.healthyapp.SessionManagment;
import com.vpace.healthyapp.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    Button login;
    EditText user,pass;
    String access_token;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.editTextEmail);
        pass=findViewById(R.id.editTextPassword);
        progress=findViewById(R.id.progress);

        login=findViewById(R.id.cirLoginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String users=user.getText().toString().trim();
                String passs=pass.getText().toString().trim();


                   progress.setVisibility(View.VISIBLE);
                 UserLogin(users,passs);




            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        CheckSession();

    }

    private void CheckSession(){
    SessionManagment    sessionManagment=new SessionManagment(LoginScreen.this);
        HashMap<String,String> map=sessionManagment.getSession();
        String token=map.get(SessionManagment.KEY_TOKEN);
        if(token!=null){

            MoveToMainScreen();
        }else{
            //do Something...
        }
    }

    private void MoveToMainScreen() {
        Intent intent=new Intent(LoginScreen.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void UserLogin(String users, String passs) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.login_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.e("LoginRes:",response);
                     //   MoveToMainScreen();
                        try {
                            JSONObject object=new JSONObject(response);
                            String status=object.getString("success");
                            if(status.contains("true")){
                                String code=object.getString("status_code");
                                if(code.contains("200")){
                                    progress.setVisibility(View.GONE);
                                    JSONObject userObj=object.getJSONObject("user");

                                    String username=userObj.getString("email");
                                     access_token=userObj.getString("token");
                                     String name=userObj.getString("username");


//                                    Log.e("Access",access_token+" "+name+""+username);
                                    if(users.contains(username)){
                                        //User userData=new User(access_token,user);


                                        Toast.makeText(LoginScreen.this, "Success", Toast.LENGTH_SHORT).show();
                                        SessionManagment sessionManagment1=new SessionManagment(LoginScreen.this);
                                        sessionManagment1.saveSession(access_token,username,name);



                                        MoveToMainScreen();



                                    }else{
                                        progress.setVisibility(View.GONE);
                                        Toast.makeText(LoginScreen.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                                    }




                                }else{
                                    progress.setVisibility(View.GONE);
                                    Toast.makeText(LoginScreen.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                progress.setVisibility(View.GONE);
                                Toast.makeText(LoginScreen.this, "Login Failed |Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.setVisibility(View.GONE);
                Toast.makeText(LoginScreen.this, "Err:"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + access_token);

                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

             //   String auth_token="4|sG7DSMiIb40PLKgnAEdgFdmCeopuqf8Yg1ULU5f4";

                map.put("email",users);
                map.put("password",passs);
               // map.put("token",access_token);
             //   map.put("Authorization", "Bearer " + auth_token);
                //map.put("",passs);
                Log.e("Map:", String.valueOf(map));
                return map;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(LoginScreen.this);
        queue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}