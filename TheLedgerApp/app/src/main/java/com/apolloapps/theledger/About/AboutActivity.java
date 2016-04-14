package com.apolloapps.theledger.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Common.AppConstants;

/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutActivity extends BaseActivity implements AboutFragment.AboutFragmentListener {

    private Intent mViewLinkIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(R.id.toolbar),getString(R.string.about),true,true);
        getFragmentManager().beginTransaction().replace(R.id.container,AboutFragment.newInstance(),null).addToBackStack(null).commit();
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
    public void donate() {
        getFragmentManager().beginTransaction().replace(R.id.container,DonateFragment.newInstance(),null).addToBackStack(null).commit();
    }

    @Override
    public void followLink(String link) {
        mViewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mViewLinkIntent.setPackage(AppConstants.GOOGLE_CHROME);
        if (mViewLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mViewLinkIntent);
        } else {
            mViewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(mViewLinkIntent);
        }
    }
}
