package com.gamitology.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.DaySpinnerItemBinding;
import com.gamitology.models.Day;

import java.util.List;

/**
 * Created by PredatorPy on 1/25/2017.
 */

public class DayAdapter extends BaseAdapter {

    private Context context;
    private List<Day> dayList;

    public DayAdapter(Context context, List<Day> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    @Override
    public int getCount() {
        return dayList.size();
    }

    @Override
    public Day getItem(int i) {
        return dayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        DaySpinnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.day_spinner_item, viewGroup, false);
        view = binding.getRoot();

        // Set day object
        Day day = getItem(i);
        binding.setDay(day);

        return view;
    }
}
