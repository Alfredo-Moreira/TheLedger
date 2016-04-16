package com.apolloapps.theledger.Help;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/15/16.
 */
public class HelpFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.send_feed_back)
    FrameLayout mSendFeedback;
    @Bind(R.id.tutorial)
    FrameLayout mViewTutorial;

    public HelpFragmentListener mListener;

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof HelpFragmentListener) {
            mListener = (HelpFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup view  = (ViewGroup) inflater.inflate(R.layout.fragment_help,container,false);
        ButterKnife.bind(this,view);
        setUp();
        return view;
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
        switch (v.getId()) {
            case R.id.send_feed_back:
                mListener.sendFeedback();
                break;
            case R.id.tutorial:
                mListener.viewTutorial();
                break;
            default:
                break;
        }

    }

    private void setUp() {
        setSelectedMenuState(R.id.lower_menu_help);
    }

    public interface HelpFragmentListener {
        void sendFeedback();
        void viewTutorial();

    }
}
