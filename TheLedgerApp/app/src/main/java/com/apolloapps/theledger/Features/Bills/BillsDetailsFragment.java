package com.apolloapps.theledger.Features.Bills;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.apolloapps.theledger.DataManager.Responses.BillDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.DateTimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 6/30/16.
 */
public class BillsDetailsFragment extends BaseFragment implements View.OnClickListener {

    private static int mBillId;
    @Bind(R.id.bill_title_text)
    TextView mBillTitle;
    @Bind(R.id.bill_type_text)
    TextView mBillType;
    @Bind(R.id.bill_due_date_text)
    TextView mBillDueDate;
    @Bind(R.id.bill_reminder_text)
    TextView mBillReminder;
    @Bind(R.id.bill_comments_text)
    TextView mBillComments;
    @Bind(R.id.view_details_edit_button)
    AppCompatButton mEditButton;
    @Bind(R.id.view_details_delete_button)
    AppCompatButton mDeleteButton;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    public BillsDetailsFragmentListener mListener;

    public static BillsDetailsFragment newInstance(int billId){
        mBillId = billId;
        return new BillsDetailsFragment();
    }


    @Override @Deprecated @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof BillsDetailsFragmentListener) {
            mListener = (BillsDetailsFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BillsDetailsFragmentListener) {
            mListener = (BillsDetailsFragmentListener) context;
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
        ViewGroup  view = (ViewGroup)inflater.inflate(R.layout.fragment_bill_details,container,false);
        ButterKnife.bind(this,view);
        setRootView(mFragmentRoot);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUp();
        getBillDetails(mBillId);

    }

    private void setUp() {
        mEditButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        setToolBarTitle(getStringResource(R.string.bills_details_action_bar_title));
    }

    private void getBillDetails(int billId) {
        mDataManager.doGetBillDetails(getUserId(), billId, new ServiceCallback<BillDetailsResponse>() {
            @Override
            public void onSuccess(BillDetailsResponse response) {
                dismissProgressBar();
                showMainScreen();
                populateBillData(response.getData());

            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                showCorrectErrorScreen(error.getErrorCode());

            }

            @Override
            public void onPreExecute() {
               showProgressBar();
                hideAllScreens();
            }
        });
    }

    private void populateBillData(BillModel model){
        mBillTitle.setText(model.getBillName());
        mBillType.setText(model.getBillType());
        mBillDueDate.setText(DateTimeUtils.getLocalizedDateFormat(model.getBillDueDate(),getActivity()));
        if(model.getIsIsReminderSet()) {
            mBillReminder.setText(setReminderDate(model.getmBillReminderDate(),getActivity()));
        } else {
            mBillReminder.setText(getString(R.string.bill_no_reminder_set));
        }
        mBillComments.setText(model.getBillComments());
    }

    private String setReminderDate(String serverDate, Context context){
        String date;
        date = DateTimeUtils.getLocalizedDateFormat(serverDate,context)+ " at "
                + DateTimeUtils.getLocalizedTimeFormat(serverDate,context);
        return date;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_details_edit_button:
                mListener.editBill(mBillId);
                break;
            case R.id.view_details_delete_button:
                mListener.deleteBill(mBillId);
                break;
            default:
                break;
        }
    }

    public interface BillsDetailsFragmentListener{
        void editBill(int billId);
        void deleteBill(int billId);
    }
}
