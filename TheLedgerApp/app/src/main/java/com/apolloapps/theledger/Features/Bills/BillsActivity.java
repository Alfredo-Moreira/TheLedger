package com.apolloapps.theledger.Features.Bills;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/21/16.
 */
public class BillsActivity extends BaseActivity implements OverFlowMenu.OverFlowMenuListener, BillsListFragment.BillsListFragmentListener,
        CreateEditBillFragment.CreateEditBillFragmentListener, BillsDetailsFragment.BillsDetailsFragmentListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        if (getIntent().getExtras() == null) {
            viewBillList();
        } else {
            createBillFragmentFromFloat();
        }
    }

    private void viewBillList() {
        getFragmentManager().beginTransaction().replace(R.id.container, BillsListFragment.newInstance(), null).commit();
    }

    private void createBillFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, CreateEditBillFragment.newInstance(false, -1), null)
                .addToBackStack(null).commit();
    }

    private void createBillFragmentFromFloat() {
        getFragmentManager().beginTransaction().replace(R.id.container, CreateEditBillFragment.newInstance(false, -1), null).commit();
    }

    @Override
    public void viewFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_BILLS:
                viewBillDetails(accountID);
                break;
            default:
                break;
        }
    }

    @Override
    public void editFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_BILLS:
                editBill(accountID);
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_BILLS:
                deleteBillFromOverflow(accountID);
                break;
            default:
                break;
        }
    }

    @Override
    public void createBill() {
        createBillFragment();
    }

    @Override
    public void editBill(int billId) {
        getFragmentManager().beginTransaction().replace(R.id.container, CreateEditBillFragment.newInstance(true, billId), null)
                .addToBackStack(null).commit();

    }

    @Override
    public void viewBillDetails(int billId) {
        getFragmentManager().beginTransaction().replace(R.id.container, BillsDetailsFragment.newInstance(billId), null)
                .addToBackStack(null).commit();
    }


    public void deleteBill(int billId) {
        final BillsDetailsFragment fragment = (BillsDetailsFragment) getFragmentManager().findFragmentById(R.id.container);
        mDataManager.doDeleteBill(billId, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                fragment.dismissProgressBar();
                fragment.showMainScreen();
                onBackPressed();
            }

            @Override
            public void onError(NetworkError error) {
                fragment.dismissProgressBar();
                fragment.showCorrectErrorScreen(error.getErrorCode());
            }

            @Override
            public void onPreExecute() {
                fragment.showProgressBar();
                fragment.hideAllScreens();
            }
        });
    }

    public void deleteBillFromOverflow(int billId) {
        final BillsListFragment fragment = (BillsListFragment) getFragmentManager().findFragmentById(R.id.container);
        mDataManager.doDeleteBill(billId, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                fragment.dismissProgressBar();
                fragment.showMainScreen();
                fragment.getBillsList();

            }

            @Override
            public void onError(NetworkError error) {
                fragment.dismissProgressBar();
                fragment.showCorrectErrorScreen(error.getErrorCode());

            }

            @Override
            public void onPreExecute() {
                fragment.showProgressBar();
                fragment.hideAllScreens();
            }
        });
    }

    @Override
    public void returnToBillList() {
        this.onBackPressed();
    }
}
