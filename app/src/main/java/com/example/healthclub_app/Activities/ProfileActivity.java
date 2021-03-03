package com.example.healthclub_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    String access_token;
    ImageView profile_image;
    TextView profile_title, profile_email, prof_name, prof_address, prof_email, prof_dob, prof_mobile;
    TabLayout tabLayout;
    ViewPager viewPager;

    ApiInterface apiInterface;
    RecyclerView res_table;
    TableAdapter tableAdapter;
    List<TableModel> tableModelList;

    ArrayList<BarEntry> FatInfo, MusleInfo, WeightInfo, VfatInfo;
    ArrayList<BarEntry> BmiInfo;
    ArrayList<String> bmi_lables;
    BarChart bmi_barChart, musle_piechart, fat_piechart, weight_piechart, vfat_barchart;
    TextView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile_image = findViewById(R.id.profilr_image);
        profile_title = (TextView) findViewById(R.id.profile_name);
        profile_email = findViewById(R.id.profile_email);
        prof_name = findViewById(R.id.prof_name);
        prof_address = findViewById(R.id.prof_address);
        prof_email = findViewById(R.id.prof_email);
        prof_dob = findViewById(R.id.prof_dob);
        prof_mobile = findViewById(R.id.prof_mobile);

        bmi_barChart = (BarChart) findViewById(R.id.Bmi_piechart);
        musle_piechart = (BarChart) findViewById(R.id.musle_piechart);
        fat_piechart = (BarChart) findViewById(R.id.fat_piechart);
        weight_piechart = (BarChart) findViewById(R.id.weight_piechart);
        vfat_barchart = (BarChart) findViewById(R.id.Vfat_piechart);
        backbtn = (TextView) findViewById(R.id.backbtn);

        res_table = findViewById(R.id.res_table);
        res_table.setHasFixedSize(true);
        res_table.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        //ArrayList...
        BmiInfo = new ArrayList<BarEntry>();
        FatInfo = new ArrayList<BarEntry>();
        MusleInfo = new ArrayList<BarEntry>();
        WeightInfo = new ArrayList<BarEntry>();
        VfatInfo = new ArrayList<BarEntry>();

        tableModelList = new ArrayList<>();

        apiInterface = NetworkInstance.getInstance().create(ApiInterface.class);


//        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
//        viewPager=(ViewPager) findViewById(R.id.progress_viewPager);

//        tabLayout.addTab(tabLayout.newTab().setText("Graph"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tablular"));

//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        access_token = getIntent().getStringExtra("Token");
        if (access_token == null) {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        } else {
            getProfileData(access_token);
            Log.e("Toekn:", "" + access_token);

        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    private void getProfileData(String access_token) {

        Call<DataObject> dataObjectCall = apiInterface.getProfileData("Bearer " + access_token);
        dataObjectCall.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {

                Log.e("ProfileData",""+response);

                UserObject getuser = response.body().getUserObject();
                UserData userData = response.body().getUserData();
                //Profile
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

                List<Progress> progressList = response.body().getProgressArrayList();
                for (int i = 0; i < progressList.size(); i++) {
                    Progress item = progressList.get(i);

                    TableModel model = new TableModel(
                            "", "", item.getWeight(), item.getVfat(), item.getTsf(),
                            item.getTotalfatpercent(), item.getBmi(), item.getBmr(),
                            item.getMusclepercent(), item.getWeektype(),
                            item.getCreated_date()
                    );
                    //Table
                    tableModelList.add(model);


                    //Graph


                    //Weight Info...
                    WeightInfo.add(new BarEntry(i, Float.parseFloat(item.getWeight())));
                    BarDataSet dataSetw = new BarDataSet(WeightInfo, "Weight");
                    dataSetw.setColors(Color.MAGENTA);

                    dataSetw.setValueTextColor(Color.BLACK);
                    dataSetw.setValueTextSize(15f);

                    BarData barDataw = new BarData(dataSetw);
                    weight_piechart.setFitBars(true);
                    weight_piechart.setData(barDataw);

                    weight_piechart.getDescription().setText("Weight Loss");
                    weight_piechart.animateY(1000);

                    //VFat Info...
                    VfatInfo.add(new BarEntry(i, Float.parseFloat(item.getVfat())));
                    BarDataSet dataSetv = new BarDataSet(VfatInfo, "VFAT");
                    dataSetv.setColors(Color.GREEN);

                    dataSetv.setValueTextColor(Color.BLACK);
                    dataSetv.setValueTextSize(15f);

                    BarData barDatav = new BarData(dataSetv);
                    vfat_barchart.setFitBars(true);
                    vfat_barchart.setData(barDatav);

                    vfat_barchart.getDescription().setText("V.FAT");
                    vfat_barchart.animateY(1000);

                    //Bmi Info...
                    BmiInfo.add(new BarEntry(i, Float.parseFloat(item.getBmi())));
                    BarDataSet dataSetb = new BarDataSet(BmiInfo, "BMI");
                    dataSetb.setColors(getColor(R.color.skyblue));
                    dataSetb.setValueTextColor(Color.BLACK);
                    dataSetb.setValueTextSize(15f);

                    BarData barData = new BarData(dataSetb);
                    bmi_barChart.setFitBars(true);
                    bmi_barChart.setData(barData);

                    bmi_barChart.getDescription().setText("Bar");
                    bmi_barChart.animateY(1000);

                    //Musle Info


                    MusleInfo.add(new BarEntry(i, Float.parseFloat(item.getMusclepercent())));
                    BarDataSet dataSetm = new BarDataSet(MusleInfo, "Muscle");
                    dataSetm.setColors(Color.RED);
                    dataSetm.setValueTextColor(Color.BLACK);
                    dataSetm.setValueTextSize(15f);

                    BarData barData1 = new BarData(dataSetm);
                    musle_piechart.setFitBars(true);
                    musle_piechart.setData(barData1);
                    musle_piechart.getDescription().setText("Bar Report");
                    musle_piechart.animateY(3000);


                    //Fat Into

                    FatInfo.add(new BarEntry(i, Float.parseFloat(item.getTotalfatpercent())));
                    BarDataSet dataSetf = new BarDataSet(FatInfo, "Fat");
                    dataSetf.setColors(Color.YELLOW);
                    dataSetf.setValueTextColor(Color.BLACK);
                    dataSetf.setValueTextSize(15f);

                    BarData barData2 = new BarData(dataSetf);
                    fat_piechart.setFitBars(true);
                    fat_piechart.setData(barData2);
                    fat_piechart.getDescription().setText("Bar Report");
                    fat_piechart.animateY(4000);


                }
                tableAdapter = new TableAdapter(ProfileActivity.this, tableModelList);
                res_table.setAdapter(tableAdapter);


            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Err:" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}