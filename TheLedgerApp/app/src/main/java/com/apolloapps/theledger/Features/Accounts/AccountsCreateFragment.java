package com.apolloapps.theledger.Features.Accounts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.AccountCreateResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/26/16.
 */
public class AccountsCreateFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.account_feature_title_text_input)
    EditText mTitle;
    @Bind(R.id.account_feature_username_text_input)
    EditText mUsername;
    @Bind(R.id.account_feature_password_text_input)
    EditText mPassword;
    @Bind(R.id.account_feature_spinner)
    Spinner mSpinner;
    @Bind(R.id.account_feature_description_text_input)
    EditText mDescription;
    @Bind(R.id.create_account_feature_button)
    Button mCreateAccount;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    private boolean mValidForm;
    public static AccountModel mEditModel;


    public AccountsCreateFragmentListener mListener;
    public static AccountsCreateFragment newInstance(AccountModel model){
        mEditModel = model;
        return new AccountsCreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof AccountsCreateFragmentListener) {
            mListener = (AccountsCreateFragmentListener) activity;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBarTitle(getStringResource(R.string.create_account));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_account_feature,container,false);
        ButterKnife.bind(this,view);
        setRootView(mFragmentRoot);
        setUp();
        return view;
    }

    private void setUp(){
        mCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_account_feature_button:
                validateForm();
                break;
            default:
                break;
        }
    }

    public interface AccountsCreateFragmentListener {
        void returnToAccountList();
    }

    private void validateForm(){
        mValidForm = true;

        if(isInvalidInput(getInput(mTitle))){
            mValidForm = false;
        }
        if(isInvalidInput(getInput(mUsername))){
            mValidForm =  false;
        }
        if(isInvalidInput(getInput(mPassword))) {
            mValidForm = false;
        }
        if(mValidForm) {
           createAccount(createAccountModel());
        } else {
            //show Bad form
        }

    }


    private void createAccount(AccountModel model) {
        mDataManager.doCreateAccount(getUserId(), model, new ServiceCallback<AccountCreateResponse>() {
            @Override
            public void onSuccess(AccountCreateResponse response) {
                dismissProgressBar();
                mListener.returnToAccountList();
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


    private AccountModel createAccountModel() {
        AccountModel model = new AccountModel();
        model.setAccountTitle(getInput(mTitle));
        model.setmAccountUsername(getInput(mUsername));
        model.setAccountPassword(getInput(mPassword));
        model.setAccountType(0);
        model.setAccountComments(getInput(mDescription));
        return model;
    }
}
