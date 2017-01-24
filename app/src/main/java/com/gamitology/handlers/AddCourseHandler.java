package com.gamitology.handlers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gamitology.database.CourseController;
import com.gamitology.fragments.CourseListFragment;
import com.gamitology.fragments.MainCom;
import com.gamitology.fragments.MainFragment;
import com.gamitology.managers.FragmentContainerManager;
import com.gamitology.models.Course;

/**
 * Created by PredatorPy on 1/23/2017.
 */

public class AddCourseHandler {

    private FragmentActivity activity;
    private MainCom mainCom;

    public AddCourseHandler(FragmentActivity activity) {
        this.activity = activity;
        this.mainCom = (MainCom) activity;
    }

    public View.OnClickListener addHandler(final Course course){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Add course to SQLiteDB
                CourseController courseController = new CourseController(activity.getApplicationContext());
                int rowId = courseController.addCourse(course);

                // If add success
                if(rowId > 0){
                    FragmentContainerManager containerManager = new FragmentContainerManager(activity);
                    MainFragment mainFragment = new MainFragment();
//                    containerManager.replaceFragment(mainFragment);
                    mainCom.replaceFragment(mainFragment);
                }

            }
        };
    }

}
