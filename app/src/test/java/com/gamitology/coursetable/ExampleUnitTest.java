package com.gamitology.coursetable;

import android.util.Log;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("TEST");
        assertEquals(4, 2 + 2);
    }

    @Test
    public void timeCal() throws Exception {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 9);
        c.set(Calendar.MINUTE, 30);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String text = df.format(c.getTime());
        System.out.println(text);

        assertEquals("09:30", text);
    }
}