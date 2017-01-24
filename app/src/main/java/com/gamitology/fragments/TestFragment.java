package com.gamitology.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gamitology.coursetable.R;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class TestFragment extends Fragment {

    private MainCom mainCom = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainCom = (MainCom)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);

        Button testBtn = (Button)view.findViewById(R.id.test_btn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test2Fragment test2Fragment = new Test2Fragment();
                mainCom.replaceFragment(test2Fragment);
            }
        });

        return view;
    }



}
