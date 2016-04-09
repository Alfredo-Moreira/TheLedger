package com.apolloapps.theledger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseActivity extends AppCompatActivity {

    public Toolbar mToolbar;

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
            case R.id.action_settings:
                break;
            case R.id.action_about:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolBar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        setActionBarTitle(title);

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    public Toolbar getToolBar(int id) {
        mToolbar = (Toolbar) findViewById(id);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        return mToolbar;
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
        return this.getBaseContext();
    }
}
