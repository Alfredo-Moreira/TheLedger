package com.apolloapps.theledger.Help;

import android.os.Bundle;
import android.view.Menu;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/15/16.
 */
public class HelpActivity extends BaseActivity implements HelpFragment.HelpFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(),getString(R.string.help),true,true);
        setUpLowerMenu(getLowerMenu(),true);
        getFragmentManager().beginTransaction().replace(R.id.container,HelpFragment.newInstance(),null).addToBackStack(null).commit();
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
    public void sendFeedback() {

    }

    @Override
    public void viewTutorial() {

    }
}
