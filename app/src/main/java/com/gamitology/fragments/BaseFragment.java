package com.gamitology.fragments;

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

/**
 * Created by PredatorPy on 1/24/2017.
 */

public class BaseFragment extends Fragment{

    public void replaceFragment(Fragment fragment){

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}
