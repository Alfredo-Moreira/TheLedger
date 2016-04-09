package com.apolloapps.theledger.Launch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/5/16.
 */
public class LaunchFragment extends BaseFragment {


    private LaunchFragmentListener mListener;

    public static LaunchFragment newInstance() {
        return new LaunchFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof LaunchFragmentListener) {
            mListener = (LaunchFragmentListener) activity;
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

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, viewGroup);
        return viewGroup;
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
    public void onResume() {
        super.onResume();
        mListener.startAnimation();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public interface LaunchFragmentListener {
        void startAnimation();
    }
}
