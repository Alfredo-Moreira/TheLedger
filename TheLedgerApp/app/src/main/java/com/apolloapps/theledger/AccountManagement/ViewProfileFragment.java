package com.apolloapps.theledger.AccountManagement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Responses.UserGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 6/7/16.
 */
public class ViewProfileFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.first_name_text)
    TextView mFirstName;
    @Bind(R.id.last_name_text)
    TextView mLastName;
    @Bind(R.id.phone_number_text)
    TextView mPhoneNumber;
    @Bind(R.id.email_address_text)
    TextView mEmailAddress;
    @Bind(R.id.create_edit_account_float_button)
    ImageView mEditAccountButton;



   public static ViewProfileFragment newInstance() {
       return new ViewProfileFragment();
    }

    public ViewProfileFragmentListener mListener;

    @Override @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ViewProfileFragmentListener) {
           mListener = (ViewProfileFragmentListener) context;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override @Deprecated @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ViewProfileFragmentListener) {
            mListener = (ViewProfileFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBarTitle(getStringResource(R.string.view_profile_action_bar_title));
        loadProfileData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_view_profile, container, false);
        ButterKnife.bind(this, view);
        setRootView(mFragmentRoot);
        setUp();
        return view;
    }

    private void setUp() {
        setSelectedMenuState(R.id.lower_menu_profile);
        mEditAccountButton.setOnClickListener(this);
        mListener.setLowerMenu();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadProfileData(){
        mDataManager.doGetUserDetails(getUserId(), new ServiceCallback<UserGetDetailsResponse>() {
            @Override
            public void onSuccess(UserGetDetailsResponse response) {
                dismissProgressBar();
                showMainScreen();
                populateData(response.getData());

            }

            @Override
            public void onError(NetworkError error) {
               dismissProgressBar();
                showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
              showProgressBar();
                hideAllScreens();
            }
        });
    }

    private void populateData(PersonalAccountModel model) {
        mFirstName.setText(model.getFirstName());
        mLastName.setText(model.getLastName());
        mPhoneNumber.setText(model.getPhoneNumber());
        mEmailAddress.setText(model.getEmailAddress());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_edit_account_float_button:
                 mListener.editProfile();
            default:
                break;
        }
    }


    public interface ViewProfileFragmentListener{
        void editProfile();
        void setLowerMenu();

    }
}
