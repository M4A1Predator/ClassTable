package com.gamitology.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.gamitology.coursetable.AppConfig;
import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.EventListItemBinding;
import com.gamitology.models.Event;

import java.util.List;

/**
 * Created by PredatorPy on 1/29/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> eventList;
    private int layoutId;

    public EventAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.eventList = objects;
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get event
        Event event = getItem(position);

        // If this is not class event
        if(event.getCourse() == null){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.event_chill_list_item, parent, false);
            return view;
        }

        // Set view layout
        EventListItemBinding binding = EventListItemBinding.inflate(LayoutInflater.from(getContext()));
        View view = binding.getRoot();
        view.findViewById(R.id.event_list_item_layout).setBackgroundColor(Color.parseColor("#10E0FF"));

        // Set data
        binding.setEvent(event);

        // Set startime text
        String startTimeText = " - ";
        startTimeText = Event.getTimeText(event.getStartTime());
        binding.setStartTime(startTimeText);

        String endTimeText = " - ";
        endTimeText = Event.getTimeText(event.getEndTime());
        binding.setEndTime(endTimeText);

        return view;
    }
}
