package com.gamitology.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gamitology.coursetable.AppConfig;
import com.gamitology.models.Event;
import com.gamitology.database.CourseTableDatabase.*;

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
        return null;
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

}
