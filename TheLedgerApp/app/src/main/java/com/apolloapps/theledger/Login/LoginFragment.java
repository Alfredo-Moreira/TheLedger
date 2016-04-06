package com.apolloapps.theledger.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Launch.LaunchFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;

/**
 * Created by AMoreira on 4/5/16.
 */
public class LoginFragment extends BaseFragment {
    @Bind(R.id.username_text_input)
    EditText mUsername;
    @Bind(R.id.password_text_input)
    EditText mPassword;
    @Bind(R.id.remember_me_switch)
    SwitchCompat mRememeberMe;
    @Bind(R.id.sign_in_button)
    Button mSignIn;
    @Bind(R.id.forgot_password_text_button)
    TextView mForgotCredentials;
    @Bind(R.id.create_account_button)
    Button mCreateAccount;

    private LoginFragmentListener mListener;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LaunchFragment.LaunchFragmentListener) {
            mListener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    }

    public interface LoginFragmentListener{
        void signIn(String username, String password, boolean rememberMe);
        void createAccount();
        void rememeberMe();
        void forgotCredentials();

    }
}
