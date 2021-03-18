package com.vpace.healthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vpace.healthyapp.Adapters.PostData;
import com.vpace.healthyapp.MainActivity;
import com.vpace.healthyapp.R;

public class Postdetails extends AppCompatActivity {


    ImageView imageView;
    TextView textView;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);

        imageView=findViewById(R.id.details_img);
        webView=findViewById(R.id.details_webview);
        textView=findViewById(R.id.details_title);

        PostData postData= (PostData) getIntent().getSerializableExtra("PostData");



        Glide.with(this)
                .load(postData.getImgUrl())
                .into(imageView);

        textView.setText(postData.getTitle());
        webView.loadData(postData.getDescription(),"","");

        webView.setBackgroundColor(Color.rgb(230,230,230));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}