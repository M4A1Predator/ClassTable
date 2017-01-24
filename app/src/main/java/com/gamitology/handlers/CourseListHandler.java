package com.gamitology.handlers;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.gamitology.database.CourseController;
import com.gamitology.fragments.CourseListFragment;
import com.gamitology.fragments.MainCom;
import com.gamitology.fragments.MainFragment;
import com.gamitology.models.Course;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class CourseListHandler {

    private FragmentActivity activity;
    private Context context;
    private MainCom mainCom;

    public CourseListHandler(Context context) {
        this.context = context;
        this.mainCom = (MainCom) context;

    }

    public View.OnClickListener deleteCourse(final Course course){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Remove Course
                CourseController courseController = new CourseController(context);
                int affectedRow = courseController.removeCourse(course);

                // If remove success
                if(affectedRow > 0){
                    // Refresh fragment
                    CourseListFragment courseListFragment = new CourseListFragment();
                    mainCom.replaceFragment(courseListFragment, true);
//                    mainCom.refreshFragment(courseListFragment);

                }
            }
        };
    }

}
