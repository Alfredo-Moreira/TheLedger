package com.apolloapps.theledger.AccountManagement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/11/16.
 */
public class CreateAccountFragment extends BaseFragment implements View.OnClickListener {

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
    FrameLayout mFragmentRoot;

    public CreateAccountFragmentListener mListener;
    private PersonalAccountModel mAccount;

    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AccountManagementActivity) {
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
        setToolBarTitle(getString(R.string.create_account));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_account_button:
                if (validateForm()) {
                    createAccount(mAccount);
                } else {
                    Toast.makeText(getActivity(),"Bad Form",Toast.LENGTH_LONG).show();
                    //handle bad form
                }
                break;
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
        if (isInvalidInput(getInput(mUsernameInput))) {
            validForm = false;
        }
        if (isInvalidInput(getInput(mPasswordInput))) {
            validForm = false;
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

    public interface CreateAccountFragmentListener {
        void login(int userId);
    }
}
