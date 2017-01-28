package com.gamitology.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gamitology.adapters.EventAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.TableFragmentLayoutBinding;
import com.gamitology.database.EventController;
import com.gamitology.models.Day;
import com.gamitology.models.Event;

import java.util.List;

/**
 * Created by PredatorPy on 1/27/2017.
 */

public class TableFragment extends Fragment {

    private Day day;
    private List<Day> dayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.dayList = Day.getDays();
        int dayIndex = this.getArguments().getInt("dayIndex");
        this.day = dayList.get(dayIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.table_fragment_layout, container, false);
        TableFragmentLayoutBinding binding = TableFragmentLayoutBinding.inflate(inflater);
        View view = binding.getRoot();

        // Get event list by day
        EventController eventController = new EventController(getContext());
        List<Event> eventList = eventController.getEventByDay(this.day);

        // Set list of events
        ListView eventListView = (ListView)view.findViewById(R.id.table_event_list);
        EventAdapter eventAdapter = new EventAdapter(getContext(), R.layout.event_list_item, eventList);
        eventListView.setAdapter(eventAdapter);

        binding.setDay(this.day);

        return view;

    }
}
