package com.apolloapps.theledger.Features.Bills;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.apolloapps.theledger.DataManager.Responses.BillGetListResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/21/16.
 */
public class BillsListFragment extends BaseFragment implements View.OnClickListener {

    public BillsListFragmentListener mListener;

    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.recycler_list_view)
    RecyclerView mBillRecyclerListView;
    @Bind(R.id.create_bill_float_button)
    ImageView mCreateBillButton;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;


    private BillsAdapter mAdapter;
    private List<BillModel> mBillList;


    public static BillsListFragment newInstance() {
        return new BillsListFragment();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BillsListFragmentListener) {
            mListener = (BillsListFragmentListener) context;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BillsListFragmentListener) {
            mListener = (BillsListFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBarTitle(getStringResource(R.string.bills_feature));
        getBillsList();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_bills, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    private void setUp() {
        setRootView(mFragmentRoot);
        mCreateBillButton.setOnClickListener(this);
        mRetryButton.setOnClickListener(this);
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

    public void getBillsList() {
        mDataManager.doGetBillList(getUserId(), new ServiceCallback<BillGetListResponse>() {
            @Override
            public void onSuccess(BillGetListResponse response) {
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

    private void setUpList(BillGetListResponse response) {
        mBillList = new ArrayList<>();
        mBillList = response.getData();
        if (mBillList.isEmpty()) {
            showSecondaryScreen(AppConstants.NO_DATA_SERVER_ERROR_SCREEN, AppConstants.NO_DATA);
        } else {
            showMainScreen();
            mAdapter = new BillsAdapter(mBillList, getActivity(), mListener);
            mBillRecyclerListView.setAdapter(mAdapter);
            mBillRecyclerListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mBillRecyclerListView.setHasFixedSize(true);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_bill_float_button:
                mListener.createBill();
                break;
            case R.id.retry_button:
                getBillsList();
                break;
            default:
                break;
        }

    }

    private void showCreateButton() {
        mCreateBillButton.setVisibility(View.VISIBLE);
    }

    private void hideCreateButton() {
        mCreateBillButton.setVisibility(View.GONE);
    }

    public interface BillsListFragmentListener {
        void createBill();

        void editBill(int billId);

        void viewBillDetails(int billId);
    }
}
