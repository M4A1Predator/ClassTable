package com.gamitology.handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.gamitology.database.EventController;
import com.gamitology.fragments.MainCom;
import com.gamitology.fragments.MainFragment;
import com.gamitology.models.Event;

/**
 * Created by PredatorPy on 2/3/2017.
 */

public class EventOptionHandler {

    private FragmentActivity activity;
    private Context context;
    private MainCom mainCom;
    private AlertDialog dialog;

    public EventOptionHandler(Context context, AlertDialog dialog) {
        this.context = context;
        this.mainCom = (MainCom) context;
        this.dialog = dialog;
    }

    public View.OnClickListener removeEvent(final Event event){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Remove
                EventController controller = new EventController(context);
                int row = controller.removeEvent(event);
                Log.d("REMOVE ==== ", row+"");
                if(row != -1){
                    dialog.dismiss();
                    MainFragment mainFragment = new MainFragment();
                    mainCom.replaceFragment(mainFragment, false);
                }else{

                }
            }
        };
    }

}
