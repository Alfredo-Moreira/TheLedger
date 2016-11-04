package com.apolloapps.theledger.Features.Accounts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/25/16.
 */
public class AccountsDetailsFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.account_title_text)
    TextView mAccountTitle;
    @Bind(R.id.account_username_text)
    TextView mAccountUsername;
    @Bind(R.id.account_password_text)
    TextView mAccountPassword;
    @Bind(R.id.account_type_text)
    TextView mAccountType;
    @Bind(R.id.account_description_text)
    TextView mAccountDescription;
    @Bind(R.id.view_details_edit_button)
    AppCompatButton mEditButton;
    @Bind(R.id.view_details_delete_button)
    AppCompatButton mDeleteButton;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;


    public AccountDetailsFragmentListener mListener;
    public static int mAccountId;
    private AccountModel mAccount;

    public static AccountsDetailsFragment newInstance(int id) {
        mAccountId = id;
        return new AccountsDetailsFragment();
    }


    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AccountDetailsFragmentListener) {
            mListener = (AccountDetailsFragmentListener) context;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof AccountDetailsFragmentListener) {
            mListener = (AccountDetailsFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
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
        setToolBarTitle(getString(R.string.account_details_action_bar_title));
        getAccountDetails();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_account_details,container,false);
        ButterKnife.bind(this,view);
        setRootView(mFragmentRoot);
        setUp();
        return view;
    }

    private void setUp() {
        mEditButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    private void getAccountDetails() {
       mDataManager.doGetAccountDetails(getUserId(), mAccountId, new ServiceCallback<AccountGetDetailsResponse>() {
           @Override
           public void onSuccess(AccountGetDetailsResponse response) {
               dismissProgressBar();
               mAccount = response.getData();
               showMainScreen();
               populateData(mAccount);
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

    private void populateData(AccountModel account){
        mAccountTitle.setText(account.getAccountTitle());
        mAccountUsername.setText(account.getAccountUsername());
        mAccountPassword.setText(account.getAccountPassword());
        mAccountType.setText(account.getAccountTypeString());
        mAccountDescription.setText(account.getAccountComments());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_details_edit_button:
                mListener.editAccount(mAccountId);
                break;
            case R.id.view_details_delete_button:
                mListener.deleteAccount(mAccountId);
                break;
            case R.id.retry_button:
                onResume();
            default:
                break;
        }
    }

    public interface AccountDetailsFragmentListener {
        void editAccount(int accountId);

        void deleteAccount(int accountId);
    }
}
