package com.apolloapps.theledger.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.apolloapps.theledger.About.AboutActivity;
import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/12/16.
 */
public class SettingsActivity extends BaseActivity implements SettingsFragment.SettingsFragmentListener {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(R.id.toolbar), getString(R.string.settings), true, true);
        getFragmentManager().beginTransaction().replace(R.id.container, SettingsFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_send_feedback:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    public void startAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }
}
