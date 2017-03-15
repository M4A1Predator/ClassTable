package com.gamitology.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gamitology.adapters.EventAdapter;
import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.EventOptionDialogBinding;
import com.gamitology.coursetable.databinding.TableFragmentLayoutBinding;
import com.gamitology.database.EventController;
import com.gamitology.handlers.EventOptionHandler;
import com.gamitology.models.Day;
import com.gamitology.models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PredatorPy on 1/27/2017.
 */

public class TableFragment extends Fragment {

    private Day day;
    private List<Day> dayList;
    private ListView eventListView;

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

        // Calculate chill time
        eventList = this.addEventChill(eventList);

        // Set list of events
        eventListView = (ListView)view.findViewById(R.id.table_event_list);
        EventAdapter eventAdapter = new EventAdapter(getContext(), R.layout.event_list_item, eventList);
        eventListView.setAdapter(eventAdapter);
        eventListView.setOnItemLongClickListener(this.displayEventOption());

        binding.setDay(this.day);

        return view;

    }

    public AdapterView.OnItemLongClickListener displayEventOption(){
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Get Event
                Event selectedEvent = (Event) eventListView.getItemAtPosition(i);

                // Set dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                EventOptionDialogBinding binding = EventOptionDialogBinding.inflate(getLayoutInflater(null));
                View eventDialog = binding.getRoot();
                binding.setEvent(selectedEvent);
                builder.setView(eventDialog);
                builder.setTitle("Event Options");

                final AlertDialog dialog = builder.create();

                EventOptionHandler handler = new EventOptionHandler(getContext(), dialog);
                binding.setHandler(handler);

                dialog.show();

                return false;
            }
        };
    }

    public List<Event> addEventChill(List<Event> eventList){

        if(eventList.size() == 0){
            return eventList;
        }

        // Prepare new list
        List<Event> newEventList = new ArrayList<Event>();

        // Get first event's time
        int endTime = eventList.get(0).getEndTime();

        // Add first event
        newEventList.add(eventList.get(0));

        // Calculate chill time event
        for(int i=1;i<eventList.size();i++){

            // Compare endtime with next start time
            int diffTime = eventList.get(i).getStartTime() - endTime;
            Log.d("DIFF TIME ==== ", diffTime+"");
            // If more than one hour
            if(diffTime > 100){
                // Add chill event
                Event chillEvent = new Event();
                newEventList.add(chillEvent);
            }
            newEventList.add(eventList.get(i));
        }

        return newEventList;
    }
}
