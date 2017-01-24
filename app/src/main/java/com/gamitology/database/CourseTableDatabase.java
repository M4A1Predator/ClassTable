package com.gamitology.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by PredatorPy on 3/27/2016.
 */
public final class CourseTableDatabase {
    public static abstract class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        public static final String ATTRIBUTE_COURSE_ID = "course_id";
        public static final String ATTRIBUTE_COURSE_CODE = "course_code";
        public static final String ATTRIBUTE_COURSE_NAME = "course_name";
        public static final String ATTRIBUTE_COURSE_DETAIL = "course_detail";

        public static void removeById(Context context, Integer id){

            CourseTableDBHelper dbHelper = new CourseTableDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String selection = ATTRIBUTE_COURSE_ID + " = ?";
            String[] selectionArgs = { id+"" };
            db.delete(TABLE_NAME, selection, selectionArgs);
        }
    }

    public static abstract class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "event";
        public static final String ATTRIBUTE_EVENT_ID = "event_id";
        public static final String ATTRIBUTE_EVENT_DAY = "event_day";
        public static final String ATTRIBUTE_EVENT_START_TIME = "event_start_time";
        public static final String ATTRIBUTE_EVENT_END_TIME = "event_end_time";
        public static final String ATTRIBUTE_EVENT_LOCATION = "event_location";
        public static final String ATTRIBUTE_EVENT_COURSE_ID = "event_course_id";
    }

}
