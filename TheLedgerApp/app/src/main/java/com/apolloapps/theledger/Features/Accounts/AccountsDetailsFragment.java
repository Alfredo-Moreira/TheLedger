package com.apolloapps.theledger.Features.Accounts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.R;

import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/25/16.
 */
public class AccountsDetailsFragment extends BaseFragment {

    public AccountDetailsFragmentListener mListener;
    private static AccountModel mAccount;

    public static AccountsDetailsFragment newInstance(AccountModel model) {
        mAccount = model;
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
        setUp();
        return view;
    }

    private void setUp(){

    }

    public interface AccountDetailsFragmentListener {

    }
}
