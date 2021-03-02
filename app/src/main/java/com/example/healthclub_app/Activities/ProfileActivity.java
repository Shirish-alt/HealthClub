package com.example.healthclub_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthclub_app.Adapters.TableAdapter;
import com.example.healthclub_app.Adapters.TableTabAdapter;
import com.example.healthclub_app.MainActivity;

import com.example.healthclub_app.Models.Profile.DataObject;
import com.example.healthclub_app.Models.Profile.Progress;
import com.example.healthclub_app.Models.Profile.UserData;
import com.example.healthclub_app.Models.Profile.UserObject;
import com.example.healthclub_app.Models.TableModel;
import com.example.healthclub_app.R;
import com.example.healthclub_app.Url.ApiInterface;
import com.example.healthclub_app.Url.NetworkInstance;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    String access_token;
    ImageView profile_image;
    TextView profile_title,profile_email,prof_name,prof_address,prof_email,prof_dob,prof_mobile;
    TabLayout tabLayout;
    ViewPager viewPager;

    ApiInterface apiInterface;
    RecyclerView res_table;
    TableAdapter tableAdapter;
    List<TableModel> tableModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile_image=findViewById(R.id.profilr_image);
        profile_title=(TextView) findViewById(R.id.profile_name);
        profile_email=findViewById(R.id.profile_email);
        prof_name=findViewById(R.id.prof_name);
        prof_address=findViewById(R.id.prof_address);
        prof_email=findViewById(R.id.prof_email);
        prof_dob=findViewById(R.id.prof_dob);
        prof_mobile=findViewById(R.id.prof_mobile);

        res_table=findViewById(R.id.res_table);
        res_table.setHasFixedSize(true);
        res_table.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        //ArrayList...

        tableModelList=new ArrayList<>();

        apiInterface= NetworkInstance.getInstance().create(ApiInterface.class);



//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        viewPager=(ViewPager) findViewById(R.id.progress_viewPager);

//        tabLayout.addTab(tabLayout.newTab().setText("Graph"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tablular"));

//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        access_token=getIntent().getStringExtra("Token");
        if(access_token==null){
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }else
        {
            getProfileData(access_token);
            Log.e("Toekn:",""+access_token);

        }


        /*tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TableTabAdapter adapter = new TableTabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount(),access_token);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    }

    private void getProfileData(String access_token) {

        Call<DataObject> dataObjectCall=apiInterface.getProfileData("Bearer "+access_token);
        dataObjectCall.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {

                UserObject getuser=response.body().getUserObject();
                UserData userData=response.body().getUserData();
                profile_title.setText(getuser.getUsername());
                profile_email.setText(getuser.getUseremail());
                prof_name.setText(getuser.getUsername());
                prof_email.setText(getuser.getUseremail());
                prof_mobile.setText(getuser.getUsermoile());
               prof_address.setText(userData.getAddress());
                prof_dob.setText(userData.getDob());

                Glide.with(ProfileActivity.this)
                        .load(getuser.getImage_url())

                        .into(profile_image);


     //Progress  Array

                List<Progress> progressList=response.body().getProgressArrayList();
                for(int i=0;i<progressList.size();i++){
                    Progress item=progressList.get(i);

                    TableModel model=new TableModel(
                            "","",item.getWeight(),item.getVfat(),item.getTsf(),
                            item.getTotalfatpercent(),item.getBmi(),item.getBmr(),
                            item.getMusclepercent(),item.getWeektype(),
                            item.getCreated_date()
                    );
                    tableModelList.add(model);

                }
                tableAdapter=new TableAdapter(ProfileActivity.this,tableModelList);
                res_table.setAdapter(tableAdapter);





            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}