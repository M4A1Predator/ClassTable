package com.gamitology.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Created by PredatorPy on 1/25/2017.
 */

public class Event {

    private int id;
    private Day day;
    private int startTime;
    private int endTime;
    private String location;
    private Course course;

    public Event() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public static String getTimeText(int time) {

        String text = " - ";

        int hour = time / 100;
        int min = time % 100;

        DecimalFormat decimalFormat = new DecimalFormat("00");

        text = decimalFormat.format(hour) + ':' + decimalFormat.format(min);

        return text;

    }

    @Override
    public String toString() {
        return "Event{" +
                "course=" + course.getCode() +
                ", id=" + id +
                ", day='" + day + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location='" + location + '\'' +
                '}';
    }



}
