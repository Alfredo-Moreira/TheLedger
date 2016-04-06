package com.apolloapps.theledger.Launch;

import android.content.Intent;
import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Login.LoginActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/5/16.
 */
public class LaunchActivity extends BaseActivity implements LaunchFragment.LaunchFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_no_actionbar);
        getFragmentManager().beginTransaction().add(LaunchFragment.newInstance(),null).addToBackStack(null).commit();
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void startLogin() {
        startActivity(new Intent(getActivityContext(), LoginActivity.class));
        this.finish();
    }
}
