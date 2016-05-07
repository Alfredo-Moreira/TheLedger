package com.apolloapps.theledger.Features.Accounts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/26/16.
 */
public class AccountsCreateFragment extends BaseFragment {


    public AccountsCreateFragmentListener mListener;
    public static AccountsCreateFragment newInstance(){
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
        return view;
    }


    private void setUp(){

    }

    public interface AccountsCreateFragmentListener {
        void createAccount();
    }
}
