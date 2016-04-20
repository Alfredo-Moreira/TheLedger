package com.apolloapps.theledger.Help;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/15/16.
 */
public class SendFeedbackFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public SendFeedbackFragmentListener mListener;
    @Bind(R.id.person_text_input)
    EditText mPersonName;
    @Bind(R.id.anonymous_checkbox)
    CheckBox mAnonymousCheckBox;
    @Bind(R.id.send_feedback_button)
    Button mSendFeedBackButton;
    @Bind(R.id.feedback_text)
    MultiAutoCompleteTextView mFeedbackText;

    public static SendFeedbackFragment newInstance() {
        return new SendFeedbackFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof SendFeedbackFragmentListener) {
            mListener = (SendFeedbackFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_send_feedback, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUp() {

        mSendFeedBackButton.setOnClickListener(this);
        mAnonymousCheckBox.setOnCheckedChangeListener(this);
        mSendFeedBackButton.setEnabled(false);
        mFeedbackText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mSendFeedBackButton.setEnabled(count > 0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSendFeedBackButton.setEnabled(count > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSendFeedBackButton.setEnabled(s.toString().length() > 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_feedback_button:
                mListener.sendFeedback(getPerson(), mFeedbackText.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setAnonymous(isChecked);
    }

    private String getPerson() {
        if (isInvalidInput(getInput(mPersonName))) {
            return getStringResource(R.string.anonymous);
        }
        return getInput(mPersonName);
    }

    private void setAnonymous(boolean isAnonymous) {
        if (isAnonymous) {
            mPersonName.getText().clear();
            mPersonName.setText(getStringResource(R.string.anonymous));
            mPersonName.setFocusable(false);
        } else {
            mPersonName.setFocusable(true);
            mPersonName.getText().clear();
        }
    }


    public interface SendFeedbackFragmentListener {
        void sendFeedback(String person, String feedback);
    }
}
