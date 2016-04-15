package com.apolloapps.theledger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected Bundle mBundle;

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


    public void setUpToolBar(Toolbar toolbar, String title, boolean setVisible, boolean setBackArrow) {
        if (setVisible) {
            setSupportActionBar(toolbar);
            setToolBarTitle(title);
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
                    BaseActivity.this.onBackPressed();
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

    public Toolbar getToolBar(int id) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        return toolbar;
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
