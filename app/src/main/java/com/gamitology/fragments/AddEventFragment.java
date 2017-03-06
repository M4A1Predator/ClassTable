package com.gamitology.fragments;

import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.gamitology.adapters.CourseOptionAdapter;
import com.gamitology.adapters.DayAdapter;
import com.gamitology.coursetable.AppConfig;
import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.AddEventFragmentLayoutBinding;
import com.gamitology.database.CourseController;
import com.gamitology.handlers.AddEventHandler;
import com.gamitology.models.Course;
import com.gamitology.models.Day;
import com.gamitology.models.Event;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class AddEventFragment extends Fragment {

    private View view;
    private AddEventFragmentLayoutBinding binding;
    private List<Day> dayList;

    private List<Course> courseList;
    private Event event;

    private EditText startTimeText;
    private EditText endTimeText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        view = inflater.inflate(R.layout.add_event_fragment_layout, container, false);
        binding = AddEventFragmentLayoutBinding.inflate(getLayoutInflater(savedInstanceState));
        view = binding.getRoot();

        // Set handler
        AddEventHandler handler = new AddEventHandler(getActivity());
        binding.setHandler(handler);

        // Init event
        event = new Event();
        binding.setEvent(event);

        // Set course option
        // Get courses
        CourseController courseController = new CourseController(getContext());
        this.courseList = courseController.getCourseList();
        // Get days
        dayList = Day.getDays();

        // set course spinner
        Spinner courseSpinner = (Spinner)view.findViewById(R.id.event_course_spinner);
        CourseOptionAdapter adapter = new CourseOptionAdapter(getContext(), courseList);
        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course selectedCourse = courseList.get(i);
                event.setCourse(selectedCourse);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                event.setCourse(null);
            }
        });

        // set day spinner
        Spinner daySpinner = (Spinner)view.findViewById(R.id.add_event_day_spinner);
        DayAdapter dayAdapter = new DayAdapter(getContext(), dayList);
        daySpinner.setAdapter(dayAdapter);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Day selectedDay = dayList.get(i);
                event.setDay(selectedDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Set start time picker
        startTimeText = (EditText)view.findViewById(R.id.add_event_start_time_edit_text);
        startTimeText.setOnClickListener(this.onClickStartTime());

        // Set end time Text
        endTimeText = (EditText)view.findViewById(R.id.add_event_end_time_edit_text);
        endTimeText.setOnClickListener(this.onClickEndTime());

        return view;
    }

    private View.OnClickListener onClickStartTime(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), this.onTimeSetListener(), hour, minute, true);
                timePickerDialog.setTitle("Start");
                timePickerDialog.show();
            }

            private TimePickerDialog.OnTimeSetListener onTimeSetListener(){
                return new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        // Set text in field
                        startTimeText.setText(i + " : " + i1);

                        // Set data format
                        String hh = AppConfig.TIME_2_CHAR_FORMAT.format(i);
                        String mm = AppConfig.TIME_2_CHAR_FORMAT.format(i1);
                        String timeNumber = hh + "" + mm;
                        int startTime = Integer.parseInt(timeNumber);
                        event.setStartTime(startTime);
                    }
                };
            }
        };
    }

    private View.OnClickListener onClickEndTime(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), this.onTimeSetListener(), hour, minute, true);
                timePickerDialog.setTitle("To");
                timePickerDialog.show();
            }

            private TimePickerDialog.OnTimeSetListener onTimeSetListener(){
                return new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        endTimeText.setText(i + " : " + i1);

                        // Set data format
                        String hh = AppConfig.TIME_2_CHAR_FORMAT.format(i);
                        String mm = AppConfig.TIME_2_CHAR_FORMAT.format(i1);
                        String timeNumber = hh + "" + mm;
                        int endTime = Integer.parseInt(timeNumber);
                        event.setEndTime(endTime);
                    }
                };
            }
        };
    }

}
