package com.apolloapps.theledger.Features.Accounts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
public class AccountsDetailsFragment extends BaseFragment {

    @Bind(R.id.fragment_root)
    FrameLayout mFragmentRoot;
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


    public AccountDetailsFragmentListener mListener;
    public static int mAccountId;
    private AccountModel mAccount;

    public static AccountsDetailsFragment newInstance(int id) {
        mAccountId = id;
        return new AccountsDetailsFragment();
    }


    @Override
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
        getAccountDetails();
        setToolBarTitle(getString(R.string.account_details_action_bar_title));
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
        return view;
    }

   private void getAccountDetails(){
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

    public interface AccountDetailsFragmentListener {

    }
}
