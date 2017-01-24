package com.gamitology.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.CourseListItemBinding;
import com.gamitology.coursetable.databinding.CourseOptionItemBinding;
import com.gamitology.handlers.CourseListHandler;
import com.gamitology.models.Course;

import java.util.List;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class CourseOptionAdapter extends BaseAdapter {

    private List<Course> courses;
    private Context context;
    private View view;

    public CourseOptionAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int i) {
        return courses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = LayoutInflater.from(context).inflate(R.layout.course_option_item, null);
//
//        // Get course
        Course course = (Course)getItem(i);
//
//        TextView codeTextView = (TextView)view.findViewById(R.id.course_option_item_code);
//        codeTextView.setText(course.getCode());

        CourseOptionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.course_option_item, viewGroup, false);
        view = binding.getRoot();

        binding.setCourse(course);

        return view;
    }
}
