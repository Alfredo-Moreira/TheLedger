package com.apolloapps.theledger.Dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AlertDialogCreator;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by AMoreira on 4/6/16.
 */
public class DashboardActivity extends BaseActivity implements DashboardFragment.DashBoardFragmentListener,
        DialogInterface.OnClickListener, DialogInterface.OnCancelListener{

    @Override
    public void onBackPressed() {
        AlertDialogCreator.showDefaultDialog(this, getString(R.string.sign_out), getString(R.string.sign_out_message),this,this,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(R.id.toolbar),getString(R.string.dashboard_action_bar_title));
        getFragmentManager().beginTransaction().replace(R.id.container,DashboardFragment.newInstance(),null).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case BUTTON_POSITIVE:
                //clear session variables TODO
                DashboardActivity.super.onBackPressed();
                break;
        }
    }

    @Override
    public void startFeature(String classPath) {
        //Disabled to prevent Crashes
        //try {
          //  startActivity(new Intent(this, Class.forName(classPath)));
       // } catch (ClassNotFoundException e) {
        // AlertDialogCreator.showDefaultDialog(this, getString(R.string.dialog_error), getString(R.string.feature_not_available),null,null,this);
    //}
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
    }
}
