package com.gamitology.coursetable;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.gamitology.database.CourseController;
import com.gamitology.database.EventController;
import com.gamitology.models.Course;
import com.gamitology.models.Event;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.gamitology.coursetable", appContext.getPackageName());
    }

    @Test
    public void insertCourseToDB(){
        // Context
        Context appContext = InstrumentationRegistry.getTargetContext();
        CourseController controller = new CourseController(appContext);

        List<Course> insertList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Course c = new Course();
            c.setId(i);
            c.setCode("c-" + i);
            c.setName("Course" + i);
            c.setDetail("Test Data");

            controller.addCourse(c);
            insertList.add(c);
        }

        // Read DB
        List<Course> courseList = new ArrayList<>();
        courseList = controller.getCourseList();

        assertEquals(insertList.size(), courseList.size());

    }

    @Test
    public void getEventList(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        EventController eventController = new EventController(appContext);

        List<Event> eventList = eventController.getEventList();

        for (Event event : eventList){
            System.out.println(event.toString());
            Log.d(" ======= ", event.toString());
        }

        assertEquals(eventList.size()+"", "1");
    }
}
