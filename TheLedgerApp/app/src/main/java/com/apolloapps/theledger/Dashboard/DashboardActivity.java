package com.apolloapps.theledger.Dashboard;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/6/16.
 */
public class DashboardActivity extends BaseActivity implements DashboardFragment.DashBoardFragmentListener{

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(R.id.toolbar),"Dashboard");
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

}
