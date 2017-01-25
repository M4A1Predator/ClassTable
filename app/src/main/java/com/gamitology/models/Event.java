package com.gamitology.models;

import java.util.Calendar;

/**
 * Created by PredatorPy on 1/25/2017.
 */

public class Event {

    private int id;
    private Day day;
    private Calendar startTime;
    private Calendar endTime;
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

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
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

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
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
