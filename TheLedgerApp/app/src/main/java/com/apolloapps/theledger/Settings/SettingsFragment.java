package com.apolloapps.theledger.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/12/16.
 */
public class SettingsFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.about_option)
    FrameLayout mAboutPage;
    @Bind(R.id.donate)
    FrameLayout mDonatePage;

    public SettingsFragmentListener mListener;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof SettingsFragmentListener) {
            mListener = (SettingsFragmentListener) activity;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    public void setUp() {
        mAboutPage.setOnClickListener(this);
        mDonatePage.setOnClickListener(this);
        setSelectedMenuState(R.id.lower_menu_setting);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_option:
                mListener.startAbout();
                break;
            case R.id.donate:
                mListener.startDonate();
                break;
            case R.id.send_feed_back:

            default:
                break;
        }
    }

    public interface SettingsFragmentListener {
        void startAbout();

        void startDonate();
    }
}
