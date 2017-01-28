package com.gamitology.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gamitology.coursetable.AppConfig;
import com.gamitology.models.Course;
import com.gamitology.models.Day;
import com.gamitology.models.Event;
import com.gamitology.database.CourseTableDatabase.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PredatorPy on 1/25/2017.
 */

public class EventController {

    private Context context;

    public EventController(Context context) {
        this.context = context;
    }

    public List<Event> getEventList(){

        List<Event> eventList = new ArrayList<>();

        // Init DB
        CourseTableDBHelper dbHelper = new CourseTableDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Set projection
        String [] projections = {
                EventEntry.ATTRIBUTE_EVENT_ID,
                EventEntry.ATTRIBUTE_EVENT_DAY,
                EventEntry.ATTRIBUTE_EVENT_START_TIME,
                EventEntry.ATTRIBUTE_EVENT_END_TIME,
                EventEntry.ATTRIBUTE_EVENT_LOCATION,
                EventEntry.ATTRIBUTE_EVENT_COURSE_ID
        };

        // Set filter
        String filter = "";

        // Get data
        String sqlString = "SELECT * FROM " + EventEntry.TABLE_NAME + " JOIN " + CourseEntry.TABLE_NAME
                + " ON " + EventEntry.ATTRIBUTE_EVENT_COURSE_ID + " = " + CourseEntry.ATTRIBUTE_COURSE_ID;
//        Cursor cursor = db.query(EventEntry.TABLE_NAME, projections, null, null, null, null, null);
        Cursor cursor = db.rawQuery(sqlString, null);

        // ORM

        while (cursor.moveToNext()){
            Event event = EventController.orm(cursor);
            eventList.add(event);
        }

        return eventList;
    }

    public int addEvent(Event event){
        // Add Event to DB

        if(event == null){
            return -1;
        }

        CourseTableDBHelper dbHelper = new CourseTableDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Prepare data

        String startTimeText = AppConfig.DATE_FORMAT.format(event.getStartTime().getTime());
        String endTimeText = AppConfig.DATE_FORMAT.format(event.getEndTime().getTime());

        String room = event.getLocation()!=null?event.getLocation():"";

        ContentValues values = new ContentValues();
        values.put(EventEntry.ATTRIBUTE_EVENT_DAY, event.getDay().getId());
        values.put(EventEntry.ATTRIBUTE_EVENT_COURSE_ID, event.getCourse().getId());
        values.put(EventEntry.ATTRIBUTE_EVENT_START_TIME, startTimeText);
        values.put(EventEntry.ATTRIBUTE_EVENT_END_TIME, endTimeText);
        values.put(EventEntry.ATTRIBUTE_EVENT_LOCATION, room);

        // Add to DB
        long rowId = db.insert(EventEntry.TABLE_NAME, null, values);
        return (int)rowId;

    }

    public List<Event> getEventByDay(Day day){

        List<Event> eventList = new ArrayList<>();

        // Init DB
        CourseTableDBHelper dbHelper = new CourseTableDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Set projection
        String [] projections = {
                EventEntry.ATTRIBUTE_EVENT_ID,
                EventEntry.ATTRIBUTE_EVENT_DAY,
                EventEntry.ATTRIBUTE_EVENT_START_TIME,
                EventEntry.ATTRIBUTE_EVENT_END_TIME,
                EventEntry.ATTRIBUTE_EVENT_LOCATION,
                EventEntry.ATTRIBUTE_EVENT_COURSE_ID
        };

        // Set filter
        String filter = "";

        // Get data
        String sqlString = "SELECT * FROM " + EventEntry.TABLE_NAME + " JOIN " + CourseEntry.TABLE_NAME
                + " ON " + EventEntry.ATTRIBUTE_EVENT_COURSE_ID + " = " + CourseEntry.ATTRIBUTE_COURSE_ID +
                " WHERE " + EventEntry.ATTRIBUTE_EVENT_DAY + " = ? ;";
        String [] sqlArgs = {
                day.getId()
        };

        Cursor cursor = db.rawQuery(sqlString, sqlArgs);

        // ORM
        while (cursor.moveToNext()){
            Event event = EventController.orm(cursor);
            eventList.add(event);
        }

        return eventList;

    }

    public static Event orm(Cursor cursor){

        Event event = new Event();

        int id = cursor.getInt(cursor.getColumnIndex(EventEntry.ATTRIBUTE_EVENT_ID));
        String day = cursor.getString(cursor.getColumnIndex(EventEntry.ATTRIBUTE_EVENT_DAY));
        String startTimeText = cursor.getString(cursor.getColumnIndex(EventEntry.ATTRIBUTE_EVENT_START_TIME));
        String endTimeText = cursor.getString(cursor.getColumnIndex(EventEntry.ATTRIBUTE_EVENT_END_TIME));
        String location = cursor.getString(cursor.getColumnIndex(EventEntry.ATTRIBUTE_EVENT_LOCATION));

        Calendar startTime = Calendar.getInstance();
        int[] startTa = EventController.getTimeLengthText(startTimeText);
        startTime.set(Calendar.HOUR_OF_DAY, startTa[0]);
        startTime.set(Calendar.MINUTE, startTa[1]);

        Calendar endTime = Calendar.getInstance();
        int[] endTa = EventController.getTimeLengthText(endTimeText);
        endTime.set(Calendar.HOUR_OF_DAY, endTa[0]);
        endTime.set(Calendar.MINUTE, endTa[1]);

        Course course = CourseController.orm(cursor);

        // Set object
        event.setId(id);
        event.setDay(Day.getDayById(day));
        event.setStartTime(endTime);
        event.setLocation(location);
        event.setCourse(course);

        return event;

    }

    public static int[] getTimeLengthText(String text){

        String [] sa = text.split(":");

        int [] timeArray = {
                Integer.parseInt(sa[0]),
                Integer.parseInt(sa[1])
        };

        return timeArray;

    }

}
