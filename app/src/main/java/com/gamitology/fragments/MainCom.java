package com.gamitology.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by PredatorPy on 1/24/2017.
 */

public interface MainCom {

    public void replaceFragment(Fragment fragment);
    public void replaceFragment(Fragment fragment, boolean addToBackStack);
    public void refreshFragment(Fragment fragment);

}
