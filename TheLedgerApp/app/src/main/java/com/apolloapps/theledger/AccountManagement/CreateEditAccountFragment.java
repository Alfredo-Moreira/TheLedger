package com.apolloapps.theledger.AccountManagement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.apolloapps.theledger.DataManager.Responses.UserGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/11/16.
 */
public class CreateEditAccountFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.first_name_text_input)
    EditText mFirstNameInput;
    @Bind(R.id.last_name_text_input)
    EditText mLastNameInput;
    @Bind(R.id.username_text_input)
    EditText mUsernameInput;
    @Bind(R.id.password_text_input)
    EditText mPasswordInput;
    @Bind(R.id.phone_number_text_input)
    EditText mPhoneNumberInput;
    @Bind(R.id.email_address_text_input)
    EditText mEmailInput;
    @Bind(R.id.create_account_button)
    Button mCreateAccount;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;

    public CreateAccountFragmentListener mListener;
    private PersonalAccountModel mAccount;

    public static CreateEditAccountFragment newInstance() {
        return new CreateEditAccountFragment();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateAccountFragmentListener) {
            mListener = (CreateAccountFragmentListener) context;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CreateAccountFragmentListener) {
            mListener = (CreateAccountFragmentListener) activity;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccount = new PersonalAccountModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_account, container, false);
        ButterKnife.bind(this, view);
        setRootView(mFragmentRoot);
        setUp();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        if (isSessionNull()) {
            setToolBarTitle(getString(R.string.create_account));
            mCreateAccount.setText(getString(R.string.create_account));
        } else {
            setToolBarTitle(getStringResource(R.string.edit_account_action_bar_title));
            mCreateAccount.setText(getString(R.string.save_changes));
            getUserDetails();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_account_button:
                createUpdateAccount();
                break;
            case R.id.retry_button:
                onResume();
                break;
            default:
                break;
        }
    }

    private void createUpdateAccount() {
        if (validateForm()) {
            if (isSessionNull()) {
                createAccount(mAccount);
            } else {
                updateProfile(mAccount);
            }
        } else {
            Toast.makeText(getActivity(), "Bad Form", Toast.LENGTH_LONG).show();
            //handle bad form
        }
    }


    private boolean validateForm() {
        boolean validForm = true;

        if (isInvalidInput(getInput(mFirstNameInput))) {
            validForm = false;
        }
        if (isInvalidInput(getInput(mLastNameInput))) {
            validForm = false;
        }
        if (!mValidator.validateUsername(getInput(mUsernameInput))) {
            validForm = false;
        }
        if (!mValidator.validatePassword(getInput(mPasswordInput))) {
            validForm = false;
        }
        if (!isInvalidInput(getInput(mEmailInput))) {
            validForm = mValidator.validateEmail(getInput(mEmailInput));
        }
        if (validForm) {
            mAccount.setPersonalAccount(getInput(mFirstNameInput), getInput(mLastNameInput),
                    getInput(mUsernameInput), getInput(mPasswordInput), getInput(mPhoneNumberInput),
                    getInput(mEmailInput));
            return true;
        }
        return false;

    }

    private void setUp() {
        mCreateAccount.setOnClickListener(this);

    }

    private void createAccount(PersonalAccountModel account) {
        mDataManager.doCreateUserAccount(account, new ServiceCallback<UserCreateAccountResponse>() {

            @Override
            public void onSuccess(UserCreateAccountResponse response) {
                dismissProgressBar();
                mListener.login(response.getId());
            }

            @Override
            public void onError(com.apolloapps.theledger.DataManager.Utilities.NetworkError error) {
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


    private void getUserDetails() {
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
                hideAllScreens();
                showProgressBar();
            }
        });
    }

    private void updateProfile(PersonalAccountModel model) {
        mDataManager.doUpdateUserAccount(getUserId(), model, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                showMainScreen();
                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
                hideAllScreens();
                showMainScreen();
            }
        });
    }

    private void populateData(PersonalAccountModel model) {
        mFirstNameInput.setText(model.getFirstName());
        mLastNameInput.setText(model.getLastName());
        mUsernameInput.setText(model.getUsername());
        mPasswordInput.setText(model.getPassword());
        mPhoneNumberInput.setText(model.getPhoneNumber());
        mEmailInput.setText(model.getEmailAddress());
    }

    public interface CreateAccountFragmentListener {
        void login(int userId);
    }


}
