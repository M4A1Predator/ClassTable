package com.gamitology.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.AddCourseFragmentLayoutBinding;
import com.gamitology.handlers.AddCourseHandler;
import com.gamitology.models.Course;

/**
 * Created by PredatorPy on 1/22/2017.
 */

public class AddCourseFragment extends Fragment {

    private View view;
    private final Course course;

    public AddCourseFragment() {
        course = new Course();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Bind data
//        final AddCourseFragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.add_course_fragment_layout, container, false);
        AddCourseFragmentLayoutBinding binding = AddCourseFragmentLayoutBinding.inflate(getLayoutInflater(savedInstanceState));
        // Set view
        view = binding.getRoot();
        binding.setCourse(course);
        binding.setHandler(new AddCourseHandler(this.getActivity()));

        return view;
    }


}
