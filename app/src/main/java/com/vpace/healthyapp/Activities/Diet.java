package com.vpace.healthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vpace.healthyapp.Adapters.DietTabAdapter;
import com.vpace.healthyapp.MainActivity;
import com.vpace.healthyapp.R;
import com.google.android.material.tabs.TabLayout;

public class Diet extends AppCompatActivity {
    String access_token;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        tabLayout=(TabLayout)findViewById(R.id.diet_tabLayout);
        viewPager=(ViewPager) findViewById(R.id.diet_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Current Diet"));
        tabLayout.addTab(tabLayout.newTab().setText("Previous Diet"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        access_token=getIntent().getStringExtra("Token");
        if(access_token==null){
            Toast.makeText(this, "Plaes Login", Toast.LENGTH_SHORT).show();
        }else
        {
            Log.e("Toekn:",""+access_token);

        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final DietTabAdapter adapter = new DietTabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount(),access_token);
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
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}