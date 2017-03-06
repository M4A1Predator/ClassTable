package com.gamitology.fragments;

/**
 * Created by PredatorPy on 1/22/2017.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamitology.adapters.MainPagerAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.models.Day;

public class MainFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate Layout
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);

        // Set viewpager
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.main_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // Get today index
        int toDayIndex = Day.getToDayInt();
        viewPager.setCurrentItem(toDayIndex);

        return view;
    }

}
