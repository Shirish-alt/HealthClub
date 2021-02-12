package com.example.healthclub_app.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthclub_app.Fragments.CurrentDiet;
import com.example.healthclub_app.Fragments.GraphFragement;
import com.example.healthclub_app.Fragments.PreviousDiet;
import com.example.healthclub_app.Fragments.TableFragement;

public class DietTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    String token;

    public DietTabAdapter(Context context, @NonNull FragmentManager fm, int behavior, String access_token) {
        super(fm);
        myContext = context;
        this.totalTabs = behavior;
        this.token=access_token;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrentDiet perviousDiet = new CurrentDiet(token);
                return perviousDiet;
            case 1:
                PreviousDiet tableFragement = new PreviousDiet(token);
                return tableFragement;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
