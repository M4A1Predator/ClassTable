package com.gamitology.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.gamitology.coursetable.AppConfig;
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
        EventListItemBinding binding = EventListItemBinding.inflate(LayoutInflater.from(getContext()));
        View view = binding.getRoot();

        // Set data
        Event event = getItem(position);

        binding.setEvent(event);

        // Set startime text
        String startTimeText = " - ";
        startTimeText = AppConfig.DATE_FORMAT.format(event.getStartTime().getTime());
        binding.setStartTime(startTimeText);

        return view;
    }
}
