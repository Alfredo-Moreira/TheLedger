package com.apolloapps.theledger;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.Common.NetworkConstants;
import com.apolloapps.theledger.DataManager.DataManager;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.Preferences.Preferences;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseFragment extends Fragment {


    protected View mRootView;
    protected ProgressBar mProgressBar;
    protected DataManager mDataManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DataManager(getActivity(), new UrlConstructor(), Preferences.INSTANCE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public String getStringResource(int id) {
        return getString(id);
    }

    public String getInput(EditText input) {
        return input.getText().toString().trim();
    }

    public int getIntResource(int id) {
        return getResources().getInteger(id);
    }

    public boolean isInvalidInput(String input) {
        if (input.isEmpty()) {
            return true;
        }
        return false;
    }


    public void setSelectedMenuState(int id) {
        RelativeLayout menu = (RelativeLayout) getActivity().findViewById(id);
        menu.setBackgroundColor(getResources().getColor(R.color.menu_selected_state));
        ((BaseActivity) getActivity()).setSelectedMenu(id);
    }

    public void showProgressBar() {
        ViewGroup layout = (ViewGroup) mRootView.findViewById(R.id.fragment_root);
        if (layout != null) {
            mProgressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
            mProgressBar.setIndeterminate(true);
            mProgressBar.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            layout.addView(mProgressBar, params);
        }
    }

    public void dismissProgressBar() {
        if (mProgressBar != null && mProgressBar.isShown()) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public int getUserId() {
        return MainApplication.getMainApplication().getUserId();
    }

    public void setToolBarTitle(String title) {
        ((BaseActivity)getActivity()).setToolBarTitle(title);
    }

    public void clearSession(){
        MainApplication.getMainApplication().clearSession();
    }

    //Screen Control methods
    public void hideAllScreens() {
        ViewGroup mainScreen = (ViewGroup) mRootView.findViewById(R.id.root);
        ViewGroup secondaryScreen = (ViewGroup) mRootView.findViewById(R.id.secondary_root);
        mainScreen.setVisibility(View.GONE);
        secondaryScreen.setVisibility(View.GONE);
    }

    public void showMainScreen() {
        ViewGroup mainScreen = (ViewGroup) mRootView.findViewById(R.id.root);
        ViewGroup secondaryScreen = (ViewGroup) mRootView.findViewById(R.id.secondary_root);
        mainScreen.setVisibility(View.VISIBLE);
        secondaryScreen.setVisibility(View.GONE);
    }

    public void showSecondaryScreen(int screen, int noDataOrServerError) {
        ViewGroup mainScreen = (ViewGroup) mRootView.findViewById(R.id.root);
        ViewGroup secondaryScreen = (ViewGroup) mRootView.findViewById(R.id.secondary_root);
        mainScreen.setVisibility(View.GONE);
        secondaryScreen.setVisibility(View.VISIBLE);
        ViewGroup noNetworkScreen = (ViewGroup) secondaryScreen.findViewById(R.id.no_network_screen);
        ViewGroup noDataOrServerScreen = (ViewGroup) secondaryScreen.findViewById(R.id.no_data_server_error_screen);

        switch (screen) {
            case AppConstants.NO_NETWORK_SCREEN:
                noNetworkScreen.setVisibility(View.VISIBLE);
                noDataOrServerScreen.setVisibility(View.GONE);
                break;

            case AppConstants.NO_DATA_SERVER_ERROR_SCREEN:
                noNetworkScreen.setVisibility(View.GONE);
                noDataOrServerScreen.setVisibility(View.VISIBLE);
                ViewGroup noData = (ViewGroup) secondaryScreen.findViewById(R.id.no_data_view);
                ViewGroup serverError = (ViewGroup) secondaryScreen.findViewById(R.id.server_error_view);

                switch (noDataOrServerError) {
                    case AppConstants.NO_DATA:
                        noData.setVisibility(View.VISIBLE);
                        serverError.setVisibility(View.GONE);
                        break;
                    case AppConstants.SERVER_ERROR:
                        noData.setVisibility(View.VISIBLE);
                        serverError.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void showCorrectErrorScreen(int errorCode) {
        switch (errorCode) {
            case NetworkConstants.STATUS_500:
                showSecondaryScreen(AppConstants.NO_DATA_SERVER_ERROR_SCREEN, AppConstants.SERVER_ERROR);
                break;
            case NetworkConstants.STATUS_0:
                showSecondaryScreen(AppConstants.NO_NETWORK_SCREEN, 0);
                break;
            default:
                break;
        }
    }

    public void setRootView(View view) {
        mRootView = view;
    }


    public void setUserId(int id) {
        MainApplication.getMainApplication().setUserId(id);
    }


}
