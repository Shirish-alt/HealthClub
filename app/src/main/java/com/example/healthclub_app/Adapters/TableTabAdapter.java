package com.example.healthclub_app.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.healthclub_app.Fragments.GraphFragement;
import com.example.healthclub_app.Fragments.TableFragement;

public class TableTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    String token;

    public TableTabAdapter(Context context, @NonNull FragmentManager fm, int behavior, String access_token) {
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
                GraphFragement graphFragement = new GraphFragement(token);
                return graphFragement;
            case 1:
                TableFragement tableFragement = new TableFragement(token);
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
