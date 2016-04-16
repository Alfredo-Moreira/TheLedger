package com.apolloapps.theledger.About;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.R;

import java.util.List;

/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutActivity extends BaseActivity implements AboutFragment.AboutFragmentListener {

    private Intent mViewLinkIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), getString(R.string.about), true, true);
        setUpLowerMenu(getLowerMenu(), true);
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
    public void followLink(String link) {
        mViewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mViewLinkIntent.setPackage(AppConstants.GOOGLE_APP);
        PackageManager manager = getPackageManager();
        List<ApplicationInfo> pa = manager.getInstalledApplications(PackageManager.GET_META_DATA);
        if (mViewLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mViewLinkIntent);
        } else {
            mViewLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(mViewLinkIntent);
        }
    }
}
