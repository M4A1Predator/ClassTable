package com.gamitology.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gamitology.models.Course;
import com.gamitology.database.CourseTableDatabase.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PredatorPy on 1/22/2017.
 */

public class CourseController {

    private Context context;

    public CourseController(Context context) {
        this.context = context;
    }

    public List<Course> getCourseList(){

        List<Course> courseList = new ArrayList<>();

        // Get Course List from DB
        CourseTableDBHelper dbHelper = new CourseTableDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Set projection
        String[] projection = {
                CourseEntry.ATTRIBUTE_COURSE_ID,
                CourseEntry.ATTRIBUTE_COURSE_CODE,
                CourseEntry.ATTRIBUTE_COURSE_NAME,
                CourseEntry.ATTRIBUTE_COURSE_DETAIL,
        };

        // Set selection
        String filter = "";

        Cursor cursor = db.query(CourseEntry.TABLE_NAME, projection, null, null, null, null, null);

        // Add data to list
        while(cursor.moveToNext()){

            Course course = new Course();

            // Set data from cursor
            int courseId = cursor.getInt(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_ID));
            String courseCode = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_CODE));
            String courseName = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_NAME));
            String courseDetail = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_DETAIL));

            // ORM
            course.setId(courseId);
            course.setCode(courseCode);
            course.setName(courseName);
            course.setDetail(courseDetail);

            // Add to list
            courseList.add(course);
        }

        return courseList;

    }

    public int addCourse(Course course){
        // Insert Course to DB

        if(course == null){
            return -1;
        }

        CourseTableDBHelper dbHelper = new CourseTableDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Prepare data
        ContentValues values = new ContentValues();
        values.put(CourseEntry.ATTRIBUTE_COURSE_CODE, course.getCode());
        values.put(CourseEntry.ATTRIBUTE_COURSE_NAME, course.getName());
        values.put(CourseEntry.ATTRIBUTE_COURSE_DETAIL, course.getDetail());

        // Insert
        long newRowId = db.insert(CourseEntry.TABLE_NAME, null, values);
        return (int)newRowId;
    }

}
