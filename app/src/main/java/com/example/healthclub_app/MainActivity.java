package com.example.healthclub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthclub_app.Activities.Diet;
import com.example.healthclub_app.Activities.LoginScreen;
import com.example.healthclub_app.Activities.MoreVideosAndPosts;
import com.example.healthclub_app.Activities.ProfileActivity;
import com.example.healthclub_app.Adapters.HomePostAdapter;
import com.example.healthclub_app.Adapters.PromoVidAdapter;
import com.example.healthclub_app.Models.PostModel;
import com.example.healthclub_app.Models.PromoVidModel;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    RecyclerView recyclerView,res_posts;
    ArrayList<PromoVidModel> promoVidModels,promoVidModels1;
    ArrayList<PostModel> postModelArrayList,postModelArrayList1;
    PromoVidAdapter promoVidAdapter,promoVidAdapter1;
    HomePostAdapter homePostAdapter;
    TextView promo_more,post_more;
    SessionManagment sessionManagment;

    String vid_id,vid_title,post_title,post_url,post_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_menu);
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.res_promo_vid);
        res_posts=findViewById(R.id.res_posts);

        promo_more=findViewById(R.id.promo_more);
        post_more=findViewById(R.id.post_more);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);

        res_posts.setLayoutManager(new LinearLayoutManager(this));
        res_posts.setHasFixedSize(true);
        toolbar.setBackgroundResource(R.color.profilePrimaryDark);



        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setItemIconTintList(null);

        toggle.syncState();

//        //ArrayLists...
        promoVidModels=new ArrayList<PromoVidModel>();
        promoVidModels1=new ArrayList<PromoVidModel>();
        postModelArrayList=new ArrayList<PostModel>();
        postModelArrayList1=new ArrayList<PostModel>();

        //Video Adapter


        promo_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MoreVideosAndPosts.class);
                intent.putExtra("MoreData","PromoMore");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        post_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MoreVideosAndPosts.class);
                intent.putExtra("MoreData","PostMore");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        sessionManagment=new SessionManagment(MainActivity.this);
        String token=sessionManagment.getSession();



        Menu sidemenu=navigationView.getMenu();
        if(token==null){
            MenuItem login_menu=sidemenu.findItem(R.id.login);
            login_menu.setVisible(true);

            MenuItem logout_menu1=sidemenu.findItem(R.id.logout);
            logout_menu1.setVisible(false);



       }
        else {
            MenuItem login_menu=sidemenu.findItem(R.id.login);
            login_menu.setVisible(false);

            MenuItem logout_menu1=sidemenu.findItem(R.id.logout);
            logout_menu1.setVisible(true);
        }







        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
//                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case  R.id.diet:
                        // Toast.makeText(MainActivity.this, "Diet", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        SessionManagment mngt=new SessionManagment(MainActivity.this);
                        String token=mngt.getSession();
                        Intent intent=new Intent(MainActivity.this, Diet.class);
                        intent.putExtra("Token",token);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        break;
                    case  R.id.progress:
                        //   Toast.makeText(MainActivity.this, "Exercise", Toast.LENGTH_SHORT).show();

                        break;
                    case  R.id.profile:
//                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();

                        SessionManagment mngt1=new SessionManagment(MainActivity.this);
                        String token1=mngt1.getSession();
//                        Log.e("Token",token1);
                        Intent intent1=new Intent(MainActivity.this, ProfileActivity.class);
                        intent1.putExtra("Token",token1);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;
                    case  R.id.login:
//                        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

//                        SessionManagment sessionManagment=new SessionManagment(MainActivity.this);
//                        sessionManagment.removeSession();
                        startActivity(new Intent(MainActivity.this, LoginScreen.class));

                        break;
                    case  R.id.logout:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        SessionManagment sessionManagment1=new SessionManagment(MainActivity.this);
                        sessionManagment1.removeSession();
                        Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();

//                        Intent intent2=new Intent(MainActivity.this,LoginScreen.class);
//                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent2);


                        break;
                }
                return true;
            }
        });
        //Load videos

        PromoVideos();


    }

    private void PromoVideos() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.promo_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Log.e("Promo-Vid:", response);

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

                            if(postModelArrayList.size() > 0) {
                                homePostAdapter = new HomePostAdapter(MainActivity.this, postModelArrayList);
                                res_posts.setAdapter(homePostAdapter);
                            }

                            //VideoAdapter
//                            promoVidAdapter = new PromoVidAdapter(MainActivity.this,promoVidModels);
                            if(promoVidModels1.size() > 0)
                            {
                                promoVidAdapter1 = new PromoVidAdapter(MainActivity.this,promoVidModels1);
                                recyclerView.setAdapter(promoVidAdapter1);
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
}