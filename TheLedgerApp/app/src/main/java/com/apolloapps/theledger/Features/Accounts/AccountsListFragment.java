package com.apolloapps.theledger.Features.Accounts;

import android.app.Activity;
import android.content.Context;
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

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
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
        mAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        setUp(getActivity());
        return view;
    }


    private void setUp(Context context) {
        mAccountsList = new ArrayList<>();
        AccountModel model = new AccountModel();
        model.setAccountId(1);
        model.setAccountTitle("Twitter");
        model.setAccountType(AppConstants.SOCIAL_ACCOUNT_TYPE);
        model.setmAccountUsername("Username abe");
        model.setAccountPassword("password abss");
        model.setAccountComments("hello world");
        mAccountsList.add(model);
        mAccountsList.add(model);
        mAdapter = new AccountsAdapter(getActivity(),mAccountsList,mListener);
        mAccountsRecyclerList.setAdapter(mAdapter);
        mAccountsRecyclerList.setLayoutManager(new LinearLayoutManager(context));
        mAccountsRecyclerList.setHasFixedSize(true);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_account_float_button:
                mListener.createAccount();
                break;
            case R.id.dismiss_sneak_peak:
                mListener.hidePeakAccount();
                break;
            default:
                break;
        }

    }

    public interface AccountsListFragmentListener {
        void viewAccountDetails(AccountModel accountModel);
        void peakAccount(AccountModel accountModel);
        void hidePeakAccount();
        void createAccount();

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

}
