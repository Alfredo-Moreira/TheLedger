package com.apolloapps.theledger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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
            case R.id.action_send_feedback:
                break;
            case R.id.action_about:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolBar(Toolbar toolbar, String title, boolean setBackArrow) {
        setSupportActionBar(toolbar);
        setActionBarTitle(title);
        setBackArrowToolbar(toolbar,setBackArrow);
    }

    public void setBackArrowToolbar(Toolbar toolbar,boolean set) {
        if(set) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.onBackPressed();
                }
            });
        }
    }

    public void setToolbarVisibility(Toolbar toolbar, boolean visible) {
        if(visible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    public void setLowerMenuVisibility(LinearLayout menu, boolean visible) {
        if(visible) {
            menu.setVisibility(View.VISIBLE);
        } else {
            menu.setVisibility(View.GONE);
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    public Toolbar getToolBar(int id) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        return toolbar;
    }

    public LinearLayout getLowerMenu(int id) {
        LinearLayout menu = (LinearLayout) findViewById(id);
        return menu;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public Context getActivityContext() {
        return this;
    }
}
