package com.gamitology.handlers;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gamitology.coursetable.AppConfig;
import com.gamitology.database.EventController;
import com.gamitology.fragments.MainCom;
import com.gamitology.fragments.MainFragment;
import com.gamitology.models.Event;

import java.util.Calendar;

/**
 * Created by PredatorPy on 1/25/2017.
 */

public class AddEventHandler {

    private FragmentActivity activity;
    private MainCom mainCom;

    public AddEventHandler(FragmentActivity activity) {
        this.activity = activity;
        this.mainCom = (MainCom)activity;
    }

    public View.OnClickListener addEvent(final Event event){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ADD EVENT ==== ", event.toString());

                boolean isFormOK = true;
                String errMsg = "";

                // Validate form
                if(event.getCourse() == null){
                    isFormOK = false;
                    errMsg = "Please select the course";
                }

                if(event.getStartTime() == null || event.getEndTime() == null){
                    isFormOK = false;
                    errMsg = "Please identify time length";
                }

                if(!isFormOK){
                    Toast.makeText(activity.getApplicationContext(), errMsg, Toast.LENGTH_SHORT).show();
                }else{

                    // Add to DB
                    EventController eventController = new EventController(activity.getApplicationContext());
                    int rowId = eventController.addEvent(event);

                    // If add success
                    if(rowId > 0){
                        MainFragment mainFragment = new MainFragment();
                        mainCom.replaceFragment(mainFragment);
                    }else{
                        Toast.makeText(activity.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };
    }

}
