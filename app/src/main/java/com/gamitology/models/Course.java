package com.gamitology.models;

/**
 * Created by PredatorPy on 1/22/2017.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Course extends BaseObservable{

    private int id;
    private String code;
    private String name;
    private String detail;

    public Course() {
    }


    // Getter and Setter
    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
//        notifyPropertyChanged(BR);
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Bindable
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
