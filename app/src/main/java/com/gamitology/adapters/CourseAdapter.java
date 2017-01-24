package com.gamitology.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gamitology.coursetable.R;
import com.gamitology.models.Course;

import java.util.List;

/**
 * Created by PredatorPy on 1/22/2017.
 */

public class CourseAdapter extends ArrayAdapter<Course> {

    private View view;
    private int layoutId;
    private List<Course> courses;

    public CourseAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CourseAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        this.layoutId = resource;
        this.courses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get view from item layout
        view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.course_list_item, null);
        }

        // Get Course
        Course course = getItem(position);

        // Visualize Data
        TextView courseName = (TextView) view.findViewById(R.id.course_list_item_name);
        courseName.setText(course.getName());

        return view;
    }
}
