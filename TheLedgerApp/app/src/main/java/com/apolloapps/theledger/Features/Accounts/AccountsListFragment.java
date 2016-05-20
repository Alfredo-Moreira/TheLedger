package com.apolloapps.theledger.Features.Accounts;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.DataManager;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.AccountGetListResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/21/16.
 */
public class AccountsListFragment extends BaseFragment implements View.OnClickListener {

    public AccountsListFragmentListener mListener;
    @Bind(R.id.recycler_list_view)
    RecyclerView mAccountsRecyclerList;
    @Bind(R.id.create_account_float_button)
    ImageView mCreateAccountButton;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;


    private AccountsAdapter mAdapter;
    private List<AccountModel> mAccountsList;
    private RelativeLayout mPeakView;


    public static AccountsListFragment newInstance() {
        return new AccountsListFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof AccountsActivity) {
            mListener = (AccountsListFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBarTitle(getString(R.string.accounts_action_bar_title));
        getAccountsList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        setRootView(mFragmentRoot);
        return view;
    }


    private void setUpList(AccountGetListResponse response) {
        mAccountsList = new ArrayList<>();
        mAccountsList = response.getData();
        mCreateAccountButton.setOnClickListener(this);
        if (mAccountsList.isEmpty()) {
            showSecondaryScreen(AppConstants.NO_DATA_SERVER_ERROR_SCREEN, AppConstants.NO_DATA);
        } else {
            showMainScreen();
            mAdapter = new AccountsAdapter(getActivity(), mAccountsList, mListener);
            mAccountsRecyclerList.setAdapter(mAdapter);
            mAccountsRecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAccountsRecyclerList.setHasFixedSize(true);
        }
    }

    private void getAccountsList() {
        mDataManager.doGetAccountList(getUserId(), new ServiceCallback<AccountGetListResponse>() {
            @Override
            public void onSuccess(AccountGetListResponse response) {
                dismissProgressBar();
                showCreateButton();
                setUpList(response);
            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                hideCreateButton();
                showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
                hideAllScreens();
                showProgressBar();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_account_float_button:
                mListener.createAccountFragment();
                break;
            case R.id.dismiss_sneak_peak:
                mListener.hidePeakAccount();
                break;
            case R.id.retry_button:
                getAccountsList();
                break;
            default:
                break;
        }

    }

    public interface AccountsListFragmentListener {
        void viewAccountDetails(int accountId);
        void editAccountDetails(AccountModel model);
        void deleteAccount(int accountId);
        void peakAccount(AccountModel accountModel);
        void hidePeakAccount();
        void createAccountFragment();

    }

    protected void peakAccountDetails(AccountModel accountModel) {
        if (mFragmentRoot != null) {
            View peak = mFragmentRoot.inflate(getActivity(),R.layout.view_account_peak,null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(800,800);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            setUpPeak(accountModel,peak);
            mFragmentRoot.addView(peak,params);
        }
    }

    protected void hidePeakAccountDetails(){

        if (mFragmentRoot != null) {
            View peak = mFragmentRoot.findViewById(R.id.view_peak_root);
            peak.setVisibility(View.GONE);
        }
    }

    private void setUpPeak(AccountModel accountModel, View view) {
        TextView accountTitle,accountUsername,accountPassword,
                accountType,accountDescription, dismissSneak;
        accountTitle = (TextView)view.findViewById(R.id.account_title_text);
        accountUsername = (TextView)view.findViewById(R.id.account_username_text);
        accountPassword = (TextView)view.findViewById(R.id.account_password_text);
        accountType = (TextView)view.findViewById(R.id.account_type_text);
        accountDescription = (TextView)view.findViewById(R.id.account_description_text);
        dismissSneak = (TextView)view.findViewById(R.id.dismiss_sneak_peak);

        accountTitle.setText(accountModel.getAccountTitle());
        accountUsername.setText(accountModel.getAccountUsername());
        accountPassword.setText(accountModel.getAccountPassword());
        accountType.setText(accountModel.getAccountTypeString());
        accountDescription.setText((accountModel.getAccountComments().isEmpty())? "No Comments" : accountModel.getAccountComments());
        dismissSneak.setOnClickListener(this);
    }

    private void hideCreateButton(){
        mCreateAccountButton.setVisibility(View.GONE);
        mRetryButton.setOnClickListener(this);
    }

    private void showCreateButton(){
        mCreateAccountButton.setVisibility(View.VISIBLE);
    }

}
