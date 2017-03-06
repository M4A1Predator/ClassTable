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

        // Set filter
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
        db.close();
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
        db.close();
        return (int)newRowId;
    }

    public int removeCourse(Course course){
        // Remove course from Table

        if(course.getId() == 0){
            return -1;
        }

        // Get DB
        CourseTableDBHelper dbHelper = new CourseTableDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Prepare data
        String filter = CourseEntry.ATTRIBUTE_COURSE_ID + " = ?";
        String [] args = { course.getId()+"" };

        // Delete
        int affectedRow = db.delete(CourseEntry.TABLE_NAME, filter, args);
        db.close();
        return affectedRow;

    }

    public Course orm(int courseId, String courseCode, String courseName, String courseDetail){

        Course course = new Course();

        // ORM
        course.setId(courseId);
        course.setCode(courseCode);
        course.setName(courseName);
        course.setDetail(courseDetail);

        return course;
    }

    public static Course orm(Cursor cursor){

        Course course = new Course();

        int courseId = cursor.getInt(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_ID));
        String courseCode = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_CODE));
        String courseName = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_NAME));
        String courseDetail = cursor.getString(cursor.getColumnIndex(CourseEntry.ATTRIBUTE_COURSE_DETAIL));

        // ORM
        course.setId(courseId);
        course.setCode(courseCode);
        course.setName(courseName);
        course.setDetail(courseDetail);

        return course;
    }

}
