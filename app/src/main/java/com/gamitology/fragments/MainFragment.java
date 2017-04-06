package com.gamitology.fragments;

/**
 * Created by PredatorPy on 1/22/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamitology.adapters.MainPagerAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.models.Day;
import com.gamitology.coursetable.AppConfig;

public class MainFragment extends Fragment {

    private int dayIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            // Get current day index from Main Activity
            int d = this.getArguments().getInt("dayIndex");
            this.dayIndex = d;

        }catch (NullPointerException ex){
            // If no current then set -1 to this dayIndex
            this.dayIndex = -1;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate Layout
        final View view = inflater.inflate(R.layout.main_fragment_layout, container, false);

        // Set viewpager
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.main_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // If no current day index
        if(this.dayIndex < 0){
            // Get today index
            int toDayIndex = Day.getToDayInt();
            this.dayIndex = toDayIndex;
        }
        // Set day index
        viewPager.setCurrentItem(this.dayIndex);

        // Set share pref table index
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConfig.CURRENT_TABLE_INDEX, this.dayIndex);
        editor.commit();

        // Set on page change
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(AppConfig.CURRENT_TABLE_INDEX, position);
                editor.commit();
                Log.d("CHANGE TO ==== ", position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

}
