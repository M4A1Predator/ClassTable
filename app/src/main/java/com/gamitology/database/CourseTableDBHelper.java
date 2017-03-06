package com.gamitology.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gamitology.database.CourseTableDatabase.*;

/**
 * Created by PredatorPy on 3/27/2016.
 */
public class CourseTableDBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "TimeTable.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_COURSE =
            "CREATE TABLE " + CourseEntry.TABLE_NAME + " (" +
                    CourseEntry.ATTRIBUTE_COURSE_ID + " INTEGER Primary Key AUTOINCREMENT," +
                    CourseEntry.ATTRIBUTE_COURSE_CODE + TEXT_TYPE + COMMA_SEP +
                    CourseEntry.ATTRIBUTE_COURSE_NAME + TEXT_TYPE + COMMA_SEP +
                    CourseEntry.ATTRIBUTE_COURSE_DETAIL + TEXT_TYPE +
                    ");";
    private static final String SQL_DROP_COURSE = "DROP TABLE IF EXISTS " + CourseEntry.TABLE_NAME;

    private static final String SQL_CREATE_EVENT =
            "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                    EventEntry.ATTRIBUTE_EVENT_ID + " INTEGER Primary Key AUTOINCREMENT," +
                    EventEntry.ATTRIBUTE_EVENT_DAY + TEXT_TYPE + COMMA_SEP +
                    EventEntry.ATTRIBUTE_EVENT_START_TIME + TEXT_TYPE + COMMA_SEP +
                    EventEntry.ATTRIBUTE_EVENT_END_TIME + TEXT_TYPE + COMMA_SEP +
                    EventEntry.ATTRIBUTE_EVENT_LOCATION + TEXT_TYPE + COMMA_SEP +
                    EventEntry.ATTRIBUTE_EVENT_COURSE_ID + " INTEGER" +
                    ");";

    private static final String SQL_DROP_EVENT = "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;

    public CourseTableDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public CourseTableDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COURSE);
        db.execSQL(SQL_CREATE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDb(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDb(db, oldVersion, newVersion);
    }

    private void updateDb(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DROP_COURSE);
        db.execSQL(SQL_DROP_EVENT);
        onCreate(db);
    }
}
