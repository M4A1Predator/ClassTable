package com.gamitology.fragments;

/**
 * Created by PredatorPy on 1/22/2017.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamitology.coursetable.R;

public class MainFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate Layout
        View view =  inflater.inflate(R.layout.main_fragment_layout, container, false);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//
//        TestFragment testFragment = new TestFragment();
//        ft.replace(R.id.main_fragment_container, testFragment);
//        ft.commit();



        return view;
    }

}
