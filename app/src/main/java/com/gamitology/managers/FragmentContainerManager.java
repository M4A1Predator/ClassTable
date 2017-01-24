package com.gamitology.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gamitology.coursetable.R;

/**
 * Created by PredatorPy on 1/23/2017.
 */

public class FragmentContainerManager {

    private FragmentActivity activity;

    public FragmentContainerManager(FragmentActivity activity) {

        this.activity = activity;

    }

    public void replaceFragment(Fragment newFragment){
        // Replace fragment in fragment_container(FrameLayout)
        // Use with main content

        FragmentTransaction fragmentTransaction = this.activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
