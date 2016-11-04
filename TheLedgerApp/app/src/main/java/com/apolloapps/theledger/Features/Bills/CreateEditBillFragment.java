package com.apolloapps.theledger.Features.Bills;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Responses.BillDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.DateTimePickerCreator;
import com.apolloapps.theledger.Utils.DateTimeUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 6/27/16.
 */
public class CreateEditBillFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    @Bind(R.id.bill_feature_title_text_input)
    EditText mBillTitle;
    @Bind(R.id.bill_feature_type_text_input)
    EditText mBillType;
    @Bind(R.id.bill_due_date)
    TextView mBillDueDate;
    @Bind(R.id.bill_feature_reminder_set_checkbox)
    CheckBox mBillReminderCheckBox;
    @Bind(R.id.bill_reminder_date_text)
    TextView mBillReminderDate;
    @Bind(R.id.bill_comments)
    MultiAutoCompleteTextView mBillComments;
    @Bind(R.id.save_changes_button)
    Button mSaveChangesButton;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;



    public CreateEditBillFragmentListener mListener;
    private static int mBillId;
    private static boolean mIsBill;
    private BillModel mBillModel;
    private boolean mCreateTimeAfter;
    private String mTemporaryDateHolder;
    private String mReminderDueDate;

    public static CreateEditBillFragment newInstance(boolean isAccount, int accountId){
        mBillId = accountId;
        mIsBill = isAccount;
        return new CreateEditBillFragment();
    }


    @Override @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CreateEditBillFragmentListener) {
            mListener = (CreateEditBillFragmentListener) context;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Override @Deprecated @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof CreateEditBillFragmentListener) {
            mListener = (CreateEditBillFragmentListener) activity;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup  view = (ViewGroup) inflater.inflate(R.layout.fragment_create_edit_bill,container,false);
        ButterKnife.bind(this,view);
        setRootView(mFragmentRoot);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    private void setUp(){
        mSaveChangesButton.setOnClickListener(this);
        mRetryButton.setOnClickListener(this);
        mBillReminderCheckBox.setOnCheckedChangeListener(this);
        mBillDueDate.setOnClickListener(this);
        mBillReminderDate.setOnClickListener(this);
        mCreateTimeAfter = false;
        if(mIsBill) {
            setToolBarTitle(getString(R.string.bill_update));
            getBillData(mBillId);
        } else {
            setToolBarTitle(getStringResource(R.string.bill_create));
        }
        if(getTextViewText(mBillDueDate).isEmpty()) {
            mBillDueDate.setText(getString(R.string.bill_click_to_due_date));
        }
    }

    private void getBillData(int billId){
        mDataManager.doGetBillDetails(getUserId(), billId, new ServiceCallback<BillDetailsResponse>() {
            @Override
            public void onSuccess(BillDetailsResponse response) {
                dismissProgressBar();
                showMainScreen();
                mBillModel = response.getData();
                populateData();
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

    private void populateData(){
        mBillTitle.setText(mBillModel.getBillName());
        mBillType.setText(mBillModel.getBillType());
        mBillDueDate.setText(DateTimeUtils.
                getLocalizedDateFormat(mBillModel.getBillDueDate(),getActivity()));
        mBillReminderCheckBox.setChecked(mBillModel.isIsRemiderSet());
        if(mBillModel.getIsIsReminderSet()) {
            mBillReminderDate.setText(reminderDateAndTimeFormat(DateTimeUtils.getLocalizedDateFormat(mBillModel.getmBillReminderDate(),getActivity()),
                    DateTimeUtils.getLocalizedTimeFormat(mBillModel.getmBillReminderDate(),getActivity())));
        }
        mBillComments.setText(mBillModel.getBillComments());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
          mBillReminderDate.setVisibility(View.VISIBLE);
            if(mIsBill) {
                if (mBillModel.getIsIsReminderSet()) {
                    mBillReminderDate.setText(reminderDateAndTimeFormat(DateTimeUtils.getLocalizedDateFormat(mBillModel.getmBillReminderDate(),getActivity()),
                            DateTimeUtils.getLocalizedTimeFormat(mBillModel.getmBillReminderDate(),getActivity())));
                } else {
                    DateTimePickerCreator.createDatePicker(getFragmentManager(), this);
                    mCreateTimeAfter = true;
                }
            } else {
                DateTimePickerCreator.createDatePicker(getFragmentManager(), this);
                mCreateTimeAfter = true;
            }
        } else {
            mBillReminderDate.setVisibility(View.GONE);
            mBillReminderDate.setText("");
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_changes_button:
                validateForm();
                break;
            case R.id.retry_button:
                this.onResume();
                break;
            case R.id.bill_due_date:
                DateTimePickerCreator.createDatePicker(getFragmentManager(),this);
                break;
            case R.id.bill_reminder_date_text:
                DateTimePickerCreator.createDatePicker(getFragmentManager(),this);
                mCreateTimeAfter =true;
                break;
            default:
                break;
        }

    }

    private void validateForm(){
        boolean validForm = true;

        if(isInvalidInput(getInput(mBillTitle))){
            validForm =false;
        }
        if (isInvalidInput(getInput(mBillType))) {
            validForm =false;
        }

        if(isInvalidInput(getTextViewText(mBillDueDate))) {
            validForm=false;
        }

        if(validForm) {
            if(!mIsBill) {
                createBillAccount(createBillModel());
            } else {
                updateBillAccount(createBillModel());
            }
        }
    }


    private void createBillAccount(BillModel model) {
        mDataManager.doCreateBill(getUserId(), model, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                showMainScreen();
                mListener.returnToBillList();
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

    private void updateBillAccount(BillModel model){
        mDataManager.doUpdateBill(model.getBillId(), model, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                showMainScreen();
                showToastShort(getString(R.string.bill_updated));
                mListener.returnToBillList();
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

    private BillModel createBillModel(){
        mBillModel = new BillModel();
        mBillModel.setBillName(getInput(mBillTitle));
        mBillModel.setBillType(getInput(mBillType));
        mBillModel.setBillDueDate(getTextViewText(mBillDueDate));
        if(mBillReminderCheckBox.isChecked()) {
            mBillModel.setIsRemiderSet(true);
            mBillModel.setmBillReminderDate(mReminderDueDate);
        } else {
            mBillModel.setIsRemiderSet(false);
            mBillModel.setmBillReminderDate(null);
        }
        if(!isInvalidInput(getInput(mBillComments))){
            mBillModel.setBillComments(getInput(mBillComments));
        }
        return mBillModel;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        view.dismiss();
        if(mCreateTimeAfter){
            mCreateTimeAfter = false;
            mTemporaryDateHolder = DateTimePickerCreator.dateConstructor(year,monthOfYear,dayOfMonth,getActivity());
            DateTimePickerCreator.createTimePicker(getActivity(),getFragmentManager(),this);
        } else {
            mBillDueDate.setText(DateTimePickerCreator.dateConstructor(year, monthOfYear, dayOfMonth,getActivity()));
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        view.removeAllViews();
        String time = DateTimePickerCreator.timeConstructor(hourOfDay,minute,getActivity());
        mBillReminderDate.setText(reminderDateAndTimeFormat(mTemporaryDateHolder,time));
    }

    private String reminderDateAndTimeFormat(String date, String time){
       mReminderDueDate = date+" "+time;
        return date+ " at " + time;
    }
    public interface CreateEditBillFragmentListener{
        void returnToBillList();
    }
}
