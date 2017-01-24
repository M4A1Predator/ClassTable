package com.gamitology.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.gamitology.adapters.CourseOptionAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.database.CourseController;
import com.gamitology.models.Course;

import java.util.List;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class AddEventFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.add_event_fragment_layout, container, false);

        // Set course option
        // Get courses
        CourseController courseController = new CourseController(getContext());
        List<Course> courseList = courseController.getCourseList();

        // set spinner
        Spinner courseSpinner = (Spinner)view.findViewById(R.id.event_course_spinner);
        CourseOptionAdapter adapter = new CourseOptionAdapter(getContext(), courseList);
        courseSpinner.setAdapter(adapter);

        return view;
    }
}
