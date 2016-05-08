package com.apolloapps.theledger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.apolloapps.theledger.Dashboard.DashboardActivity;
import com.apolloapps.theledger.DataManager.DataManager;
import com.apolloapps.theledger.Help.HelpActivity;
import com.apolloapps.theledger.Login.LoginActivity;
import com.apolloapps.theledger.Settings.SettingsActivity;
import com.apolloapps.theledger.Utils.AlertDialogCreator;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    protected Bundle mBundle;
    protected int mSelectedMenu;
    protected DataManager mDataManager;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                AlertDialogCreator.showDefaultDialog(this, getString(R.string.sign_out), getString(R.string.sign_out_message), this, this, null);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolBar(Toolbar toolbar, boolean setVisible, boolean setBackArrow) {
        if (setVisible) {
            setSupportActionBar(toolbar);
            setBackArrowToolbar(toolbar, setBackArrow);
            setToolbarVisibility(toolbar, setVisible);
        } else {
            setToolbarVisibility(toolbar, setVisible);
        }
    }


    private void setBackArrowToolbar(Toolbar toolbar, boolean set) {
        if (set) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 onBackPressed();
                }
            });
        }
    }

    private void setToolbarVisibility(Toolbar toolbar, boolean visible) {
        if (visible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    public void setToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    public Toolbar getToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        return toolbar;
    }

    public void setUpLowerMenu(LinearLayout layout, boolean visible) {

        if (visible) {
            layout.setVisibility(View.VISIBLE);
            setUpOnClickListener();
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    public LinearLayout getLowerMenu() {
        LinearLayout lowerMenu = (LinearLayout) findViewById(R.id.secondary_menus_container);
        return lowerMenu;
    }

    private void setUpOnClickListener() {
        findViewById(R.id.lower_menu_home).setOnClickListener(this);
        findViewById(R.id.lower_menu_profile).setOnClickListener(this);
        findViewById(R.id.lower_menu_help).setOnClickListener(this);
        findViewById(R.id.lower_menu_setting).setOnClickListener(this);
    }

    public void setSelectedMenu(int id) {
        mSelectedMenu = id;
    }

    private boolean isMenuSelected(int id) {
        if (mSelectedMenu == id) {
            return true;
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBundle != null) {
            mBundle.clear();
        }
    }

    public Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case BUTTON_POSITIVE:
                //clear session variables TODO
                backToLogin();
                break;
        }
    }

    private void backToLogin() {
        startActivity(new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lower_menu_home:
                if (!isMenuSelected(R.id.lower_menu_home)) {
                    startDashboard();
                }
                break;
            case R.id.lower_menu_profile:
                if (!isMenuSelected(R.id.lower_menu_profile)) {
                    startProfile();
                }
                break;
            case R.id.lower_menu_help:
                if (!isMenuSelected(R.id.lower_menu_help)) {
                    startHelp();
                }
                break;
            case R.id.lower_menu_setting:
                if (!isMenuSelected(R.id.lower_menu_setting)) {
                    startSettings();
                }
                break;
            default:
                break;
        }
    }

    private void startDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void startProfile() {
        //placeholder
    }

    private void startHelp() {
        startActivity(new Intent(this, HelpActivity.class));
    }

    private void startSettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    public void clearSession(){
        MainApplication.getMainApplication().mSessionStorage.clearSession();
    }


}
