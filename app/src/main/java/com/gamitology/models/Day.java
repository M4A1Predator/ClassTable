package com.gamitology.models;

/**
 * Created by PredatorPy on 1/22/2017.
 */

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Day {
    private String id;
    private String name;

    public static final List<Day> DAYS = getDays();

    public Day() {
    }

    public Day(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Day(JSONObject dayJson){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Day{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;

        Day day = (Day) o;

        if (getId() != null ? !getId().equals(day.getId()) : day.getId() != null) return false;
        return !(getName() != null ? !getName().equals(day.getName()) : day.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public static List<Day> getDays(){
        List<Day> days = new ArrayList<>();
        days.add(new Day("mon", "monday"));
        days.add(new Day("tue", "tuesday"));
        days.add(new Day("wed", "wednesday"));
        days.add(new Day("thu", "thursday"));
        days.add(new Day("fri", "friday"));
        days.add(new Day("sat", "saturday"));

        return days;
    }

    public static Day getDayById(String dayId){
        List<Day> dayList = Day.getDays();
        Day day = new Day();
        for(Day d:dayList){
            if(d.getId().equals(dayId)){
                day = d;
                break;
            }
        }

        return day;
    }


    public static Day getToDay(){

        Calendar c = Calendar.getInstance();
        List<Day> dayList = Day.getDays();

        Day day = null;

        int dow = c.get(Calendar.DAY_OF_WEEK);
        switch (dow){
            case Calendar.MONDAY: day = dayList.get(0);
            case Calendar.TUESDAY: day = dayList.get(1);
            case Calendar.WEDNESDAY: day = dayList.get(2);
            case Calendar.THURSDAY: day = dayList.get(3);
            case Calendar.FRIDAY: day = dayList.get(4);
            case Calendar.SATURDAY: day = dayList.get(5);
        }

        return day;
    }

    public static int getToDayInt(){

        Calendar c = Calendar.getInstance();
        List<Day> dayList = Day.getDays();

        int index = 0;

        int dow = c.get(Calendar.DAY_OF_WEEK);
        switch (dow){
            case Calendar.MONDAY: index = 0;
            case Calendar.TUESDAY: index = 1;
            case Calendar.WEDNESDAY: index  = 2;
            case Calendar.THURSDAY: index  = 3;
            case Calendar.FRIDAY: index = 4;
            case Calendar.SATURDAY: index = 5;
        }

        return index;
    }

    public static Day getDayByIndex(int i){
        List<Day> dayList = Day.getDays();
        if(i < dayList.size()){
            return dayList.get(i);
        }
        return null;
    }

}

