package com.apolloapps.theledger.Login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/5/16.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @Bind(R.id.username_text_input)
    EditText mUsername;
    @Bind(R.id.password_text_input)
    EditText mPassword;


    private LoginFragmentListener mListener;
    private boolean mRemembered;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof LoginFragmentListener) {
            mListener = (LoginFragmentListener) activity;
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
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, viewGroup);
        return viewGroup;
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                mListener.signIn(getInput(mUsername), getInput(mPassword), mRemembered);
                break;
            case R.id.forgot_password_text_button:
                mListener.forgotCredentials();
                break;
            case R.id.create_account_view_button:
                mListener.createAccount();
                break;
            default:
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.remember_me_switch:
                if (isChecked) {
                    buttonView.setChecked(!isChecked);

                } else {
                    buttonView.setChecked(isChecked);

                }
                break;
            default:
                break;
        }
    }

    public interface LoginFragmentListener{
        void signIn(String username, String password, boolean rememberMe);
        void createAccount();
        void forgotCredentials();

    }
}
