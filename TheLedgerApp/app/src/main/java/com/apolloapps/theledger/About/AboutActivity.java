package com.apolloapps.theledger.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutActivity extends BaseActivity implements AboutFragment.AboutFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        startAboutFragment();

    }


    private void startAboutFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, AboutFragment.newInstance(), null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void followLink(String link) {
        Intent viewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        if (viewLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(viewLinkIntent);
        } else {
            viewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(viewLinkIntent);
        }
    }
}
