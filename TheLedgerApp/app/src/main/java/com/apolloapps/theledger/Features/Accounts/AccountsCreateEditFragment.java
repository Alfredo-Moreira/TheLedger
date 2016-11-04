package com.apolloapps.theledger.Features.Accounts;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/26/16.
 */
public class AccountsCreateEditFragment extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.account_feature_title_text_input)
    EditText mTitle;
    @Bind(R.id.account_feature_username_text_input)
    EditText mUsername;
    @Bind(R.id.account_feature_password_text_input)
    EditText mPassword;
    public static boolean mIsAccount;
    public static int mAccountId;
    @Bind(R.id.account_feature_type_view)
    RadioGroup mAccountTypeRadio;
    @Bind(R.id.radio_other)
    RadioButton mOtherRadioButton;
    @Bind(R.id.radio_social)
    RadioButton mSocialRadioButton;
    @Bind(R.id.account_feature_description_text_input)
    EditText mDescription;
    @Bind(R.id.radio_financial)
    RadioButton mFinancialRadioButton;
    @Bind(R.id.radio_entertainment)
    RadioButton mEntertainmentRadioButton;
    @Bind(R.id.create_account_feature_button)
    Button mCreateSaveButton;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    private RadioButton mSelectedRadio;
    public static AccountModel mEditModel;


    public AccountsCreateFragmentListener mListener;

    public static AccountsCreateEditFragment newInstance(boolean isAccount, int accountId) {
        mIsAccount = isAccount;
        mAccountId = accountId;
        return new AccountsCreateEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AccountsCreateFragmentListener) {
            mListener = (AccountsCreateFragmentListener) context;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
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
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUp();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_account_feature,container,false);
        ButterKnife.bind(this,view);
        setRootView(mFragmentRoot);
        return view;
    }

    private void setUp(){
        mCreateSaveButton.setOnClickListener(this);
        mAccountTypeRadio.setOnCheckedChangeListener(this);
        if (!mIsAccount) {
            mCreateSaveButton.setText(getString(R.string.create_account));
            setToolBarTitle(getStringResource(R.string.create_account));
        } else {
            mCreateSaveButton.setText(getString(R.string.save_changes));
            setToolBarTitle(getStringResource(R.string.edit_account_action_bar_title));
            getAccountModel(mAccountId);
        }
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mSelectedRadio = (RadioButton) getActivity().findViewById(checkedId);
    }

    public interface AccountsCreateFragmentListener {
        void returnToAccountList();
    }

    private void validateForm(){
        boolean mValidForm = true;

        if(isInvalidInput(getInput(mTitle))){
            mValidForm = false;
        }
        if(isInvalidInput(getInput(mUsername))){
            mValidForm =  false;
        }
        if(isInvalidInput(getInput(mPassword))) {
            mValidForm = false;
        }
        if (mSelectedRadio == null) {
            mValidForm = false;
        }
        if(mValidForm) {
            if (!mIsAccount) {
                createAccount(createAccountModel());
            } else {
                updateAccount(createAccountModel());
            }
        } else {
            //show Bad form
        }

    }



    private void createAccount(AccountModel model) {
        mDataManager.doCreateAccount(getUserId(), model, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
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

    private void updateAccount(AccountModel model) {
        mDataManager.doUpdateAccount(mAccountId, model, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                showMainScreen();
                showToastShort(getString(R.string.account_updated));
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


    private void populateData() {
        if (mEditModel != null) {
            mTitle.setText(mEditModel.getAccountTitle());
            mUsername.setText(mEditModel.getAccountUsername());
            mPassword.setText(mEditModel.getAccountPassword());
            setChecked(mEditModel.getAccountType());
            mAccountTypeRadio.setOnCheckedChangeListener(this);
            mDescription.setText(mEditModel.getAccountComments());
        }
    }

    private void getAccountModel(int id) {

        mDataManager.doGetAccountDetails(getUserId(), id, new ServiceCallback<AccountGetDetailsResponse>() {
            @Override
            public void onSuccess(AccountGetDetailsResponse response) {
                dismissProgressBar();
                mEditModel = new AccountModel();
                mEditModel = response.getData();
                populateData();
                showMainScreen();
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
        model.setAccountUsername(getInput(mUsername));
        model.setAccountPassword(getInput(mPassword));
        model.setAccountType(model.getAccountTypeInt(mSelectedRadio.getText().toString()));
        model.setAccountComments(getInput(mDescription));
        return model;
    }

    private void setChecked(int checked) {
        switch (checked) {
            case AppConstants.OTHER_ACCOUNT_TYPE:
                mOtherRadioButton.setChecked(true);
                break;
            case AppConstants.SOCIAL_ACCOUNT_TYPE:
                mSocialRadioButton.setChecked(true);
                break;
            case AppConstants.FINANCIAL_ACCOUNT_TYPE:
                mFinancialRadioButton.setChecked(true);
                break;
            case AppConstants.ENTERTAINMENT_ACCOUNT_TYPE:
                mEntertainmentRadioButton.setChecked(true);
                break;
            default:
                break;
        }
    }


}
