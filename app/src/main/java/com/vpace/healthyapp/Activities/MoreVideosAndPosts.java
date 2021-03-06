package com.vpace.healthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vpace.healthyapp.Adapters.MorePostAdapter;
import com.vpace.healthyapp.Adapters.MoreVideosAdapter;
import com.vpace.healthyapp.MainActivity;
import com.vpace.healthyapp.Models.PostModel;
import com.vpace.healthyapp.Models.PromoVidModel;
import com.vpace.healthyapp.R;
import com.vpace.healthyapp.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoreVideosAndPosts extends AppCompatActivity {

    String moredata=" ";
    String vid_id,vid_title,post_title,post_url,post_desc;
    MoreVideosAdapter promoVidAdapter,promoVidAdapter1;
    MorePostAdapter homePostAdapter;
    ArrayList<PromoVidModel> promoVidModels,promoVidModels1;
    ArrayList<PostModel> postModelArrayList,postModelArrayList1;

    RecyclerView res_more_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_videos_and_posts);

        res_more_data=findViewById(R.id.res_more_data);

        moredata=getIntent().getStringExtra("MoreData");

        if(moredata.contains("PromoMore")){
          //  Toast.makeText(this, "More Videos", Toast.LENGTH_SHORT).show();

            LoadDataFromServer(moredata);

        }else  if(moredata.contains("PostMore")){
           // Toast.makeText(this, "More Posts", Toast.LENGTH_SHORT).show();


            LoadDataFromServer(moredata);


        }


//recycles.....

  res_more_data.setHasFixedSize(true);
        res_more_data.setLayoutManager(new LinearLayoutManager(this));


//        //ArrayLists...
        promoVidModels=new ArrayList<PromoVidModel>();
        promoVidModels1=new ArrayList<PromoVidModel>();
        postModelArrayList=new ArrayList<PostModel>();
        postModelArrayList1=new ArrayList<PostModel>();



    }

    private void LoadDataFromServer(String moredata) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.promo_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Promo-Vid:", response);

                        try {
                            JSONObject object=new JSONObject(response);
                            String obj=object.getString("success");
                            if(obj.contains("true")){


                                //Vidoes...................
                                JSONArray arrayVideo=object.getJSONArray("videos");
                                for(int i=0;i<arrayVideo.length();i++){
                                    JSONObject object1 =arrayVideo.getJSONObject(i);

                                    vid_id=object1.getString("videoid");
                                    vid_title=object1.getString("title");


                                    PromoVidModel promoVidModel=new PromoVidModel(vid_id,vid_title);
                                    promoVidModels.add(promoVidModel);


                                }
                                JSONArray arrayVideo1=object.getJSONArray("videos");
                                for(int i=0;i<arrayVideo1.length();i++){
                                    JSONObject object1 =arrayVideo1.getJSONObject(i);

                                    vid_id=object1.getString("videoid");
                                    vid_title=object1.getString("title");


                                    PromoVidModel promoVidModel=new PromoVidModel(vid_id,vid_title);
                                    promoVidModels1.add(promoVidModel);


                                }




                                    //Posts...........................................................
                                    JSONArray arrayPost =object.getJSONArray("posts");
                                    for(int i1=0;i1<arrayPost.length();i1++){
                                        JSONObject objectPost=arrayPost.getJSONObject(i1);

                                        post_title=objectPost.getString("title");
                                        post_desc=objectPost.getString("description");

                                        JSONArray arrayImages=objectPost.getJSONArray("images");
                                        for(int img1=0;img1<arrayImages.length();img1++){
                                            JSONObject objImg=arrayImages.getJSONObject(img1);

                                            post_url=objImg.getString("post_image");


                                            PostModel postModel=new PostModel(post_url,post_title,post_desc);
                                            postModelArrayList.add(postModel);
                                        }


                                    }







                            }

                            //postAdapter
                    if(moredata.contains("PostMore")){
                        homePostAdapter = new MorePostAdapter(MoreVideosAndPosts.this,postModelArrayList);
                        res_more_data.setAdapter(homePostAdapter);

                    }else if(moredata.contains("PromoMore")){
                        //VideoAdapter
                        promoVidAdapter = new MoreVideosAdapter(MoreVideosAndPosts.this,promoVidModels);
                        promoVidAdapter1 = new MoreVideosAdapter(MoreVideosAndPosts.this,promoVidModels1);
                        res_more_data.setAdapter(promoVidAdapter1);
                    }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Err:" + error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}