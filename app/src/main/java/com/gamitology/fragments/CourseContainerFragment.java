package com.gamitology.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamitology.coursetable.R;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class CourseContainerFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.course_fragment_layout, container, false);

        // Inflate course list fragment
        CourseListFragment courseListFragment = new CourseListFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, courseListFragment);
        ft.addToBackStack(null);
        ft.commit();

        return view;
    }

}
