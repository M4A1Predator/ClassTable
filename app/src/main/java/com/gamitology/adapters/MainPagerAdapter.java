package com.gamitology.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.BaseAdapter;

import com.gamitology.fragments.TableFragment;
import com.gamitology.models.Day;

import java.util.List;

/**
 * Created by PredatorPy on 1/28/2017.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        // Get day
        List<Day> dayList = Day.getDays();
        Day day = dayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("dayIndex", position);
        TableFragment fragment = new TableFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Day day = Day.getDayByIndex(position);
        String title = day.getId().substring(0, 1).toUpperCase() + day.getId().substring(1);

        return title;
    }
}
