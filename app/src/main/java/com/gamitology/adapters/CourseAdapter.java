package com.gamitology.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.CourseListItemBinding;
import com.gamitology.handlers.CourseListHandler;
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
        CourseListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.course_list_item, parent, false);
        view = binding.getRoot();

        // Get Course
        Course course = getItem(position);
        binding.setCourse(course);
        binding.setHandler(new CourseListHandler(getContext()));

        return view;
    }
}
