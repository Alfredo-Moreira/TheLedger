package com.apolloapps.theledger.Launch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Login.LoginActivity;
import com.apolloapps.theledger.Login.LoginFragment;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AppConstants;

/**
 * Created by AMoreira on 4/5/16.
 */
public class LaunchFragment extends BaseFragment {


    private Runnable mRunnable;
    private LaunchFragmentListener mListener;

    public static LaunchFragment newInstance() {
        return  new LaunchFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        if(activity instanceof LaunchFragmentListener) {
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
        return super.onCreateView(inflater, container, savedInstanceState);

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
        createRunnable();
        mRunnable.run();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void createRunnable() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(AppConstants.FIVE_SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mListener.startLogin();
            }
        };
    }
    public interface LaunchFragmentListener {
        void startLogin();
    }
}
