package com.gamitology.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.gamitology.adapters.CourseAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.database.CourseController;
import com.gamitology.models.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PredatorPy on 1/22/2017.
 */

public class CourseListFragment extends Fragment {

    private View view;
    private MainCom mainCom;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mainCom = (MainCom)context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Set Inflate view
        view = inflater.inflate(R.layout.course_list_fragment_layout, container, false);

        // Get course List
        List<Course> courseList = null;
        CourseController courseController = new CourseController(getContext());
        courseList = courseController.getCourseList();

        // Display Listview
        ListView courseListView = (ListView) view.findViewById(R.id.course_list_view);
        CourseAdapter courseAdapter = new CourseAdapter(getContext(), R.layout.course_list_item, courseList);
        courseListView.setAdapter(courseAdapter);

        // Set Events
        // Add Course Button
        Button addCourseButton = (Button) view.findViewById(R.id.add_course_btn);
        addCourseButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // Get fragment
                AddCourseFragment addCourseFragment = new AddCourseFragment();

                // Replace fragment
                mainCom.replaceFragment(addCourseFragment);
            }
        });

        return view;
    }

}
