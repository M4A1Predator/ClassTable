package com.gamitology.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gamitology.coursetable.R;
import com.gamitology.coursetable.databinding.Test2FragmentBinding;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class Test2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Test2FragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.test2_fragment, container, false);
//        View view = inflater.inflate(R.layout.test2_fragment, container, false);
        View view = binding.getRoot();
        binding.setTv(1);

        return view;
    }


}
