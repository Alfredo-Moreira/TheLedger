package com.apolloapps.theledger.Features.CheckList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.CheckListModel;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Responses.CheckListGetListResponse;
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
public class CheckListFragment extends BaseFragment implements View.OnClickListener {

    public CheckListFragmentListener mListener;
    @Bind(R.id.fragment_root)
    RelativeLayout mFragmentRoot;
    @Bind(R.id.check_list_recyclerview)
    RecyclerView mCheckListRecyclerList;
    @Bind(R.id.create_checklist_float_button)
    ImageView mCreateCheckListButton;
    @Bind(R.id.delete_checklist_float_button)
    ImageView mDeleteCheckListButton;
    @Bind(R.id.retry_button)
    RelativeLayout mRetryButton;
    @Bind(R.id.checklist_tab_todo)
    TextView mTodoTabViewButton;
    @Bind(R.id.checklist_tab_completed)
    TextView mCompletedTabViewButton;
    private EditText mCreateCheckListItemInput;
    private View mCreateCheckListView;
    private List<CheckListModel> mCheckListList;
    private CheckListAdapter mAdapter;
    private TextView mCreateCheckListItemButton,mDismissCheckListItemButton;
    private int mSelectedTab;

    public static CheckListFragment newInstance() {
        return new CheckListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_checklist, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    private void setUp() {
        setRootView(mFragmentRoot);
        mCreateCheckListButton.setOnClickListener(this);
        mDeleteCheckListButton.setOnClickListener(this);
        mTodoTabViewButton.setOnClickListener(this);
        mCompletedTabViewButton.setOnClickListener(this);
        setSelectedTab(mTodoTabViewButton);
        getTodoCheckList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CheckListFragmentListener) {
            mListener = (CheckListFragmentListener) context;
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
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CheckListFragmentListener) {
            mListener = (CheckListFragmentListener) activity;
        } else {
            throw new RuntimeException(getString(R.string.listener_not_implemented));
        }
    }

    private void getTodoCheckList() {
        mDataManager.doGetChecklistList(getUserId(),AppConstants.TODO_CHECKLIST, new ServiceCallback<CheckListGetListResponse>() {
            @Override
            public void onSuccess(CheckListGetListResponse response) {
                dismissProgressBar();
                showRightFloatButton();
                setUpList(response);
            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                hideCreateDeleteButton();
                showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
                hideAllScreens();
                showProgressBar();
            }
        });

    }
    private void getCompletedCheckList() {
        mDataManager.doGetChecklistList(getUserId(),AppConstants.COMPLETED_CHECKLIST, new ServiceCallback<CheckListGetListResponse>() {
            @Override
            public void onSuccess(CheckListGetListResponse response) {
                dismissProgressBar();
                showRightFloatButton();
                setUpList(response);
            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                hideCreateDeleteButton();
                showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
                hideAllScreens();
                showProgressBar();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBarTitle(getStringResource(R.string.checklist_action_bar_title));
    }


    private void setSelectedTab(TextView tab) {
        tab.setBackgroundColor(getResources().getColor(R.color.tab_pressed_state));
        mSelectedTab = tab.getId();
    }

    private void resetSelectedTab() {
        mTodoTabViewButton.setBackground(getResources().getDrawable(R.drawable.tab_list_selector));
        mCompletedTabViewButton.setBackground(getResources().getDrawable(R.drawable.tab_list_selector));
    }

    private void showRightFloatButton() {
        if(mSelectedTab == mTodoTabViewButton.getId()) {
            mCreateCheckListButton.setVisibility(View.VISIBLE);
            mDeleteCheckListButton.setVisibility(View.GONE);
        } else {
            mCreateCheckListButton.setVisibility(View.GONE);
            mDeleteCheckListButton.setVisibility(View.VISIBLE);
        }
    }

    private void loadCorrectTab() {
        if(mSelectedTab == mTodoTabViewButton.getId()) {
            mTodoTabViewButton.performClick();
        } else {
            mCompletedTabViewButton.performClick();
        }
    }


    private void setUpList(CheckListGetListResponse response) {
        mCheckListList = new ArrayList<>();
        mCheckListList = response.getData();
        if (mCheckListList.isEmpty()) {
            showSecondaryScreen(AppConstants.NO_DATA_SERVER_ERROR_SCREEN, AppConstants.NO_DATA);
        } else {
            showMainScreen();
            mAdapter = new CheckListAdapter(mCheckListList, getActivity(), mListener);
            mCheckListRecyclerList.setAdapter(mAdapter);
            mCheckListRecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mCheckListRecyclerList.setHasFixedSize(true);
        }
    }

    private void createCheckListItemView() {
        if (mFragmentRoot != null) {
            mCreateCheckListView = View.inflate(getActivity(), R.layout.view_checklist_create, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(800, 400);
            mCreateCheckListItemButton = (TextView) mCreateCheckListView.findViewById(R.id.create_checklist_item_button);
            mDismissCheckListItemButton = (TextView) mCreateCheckListView.findViewById(R.id.dismiss_checklist_item_button);
            mCreateCheckListItemButton.setOnClickListener(this);
            mDismissCheckListItemButton.setOnClickListener(this);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            darkScreenColor();
            hideCreateDeleteButton();
            mFragmentRoot.addView(mCreateCheckListView, params);
        }
    }

    public void performCreateClick(){
        mCreateCheckListButton.performClick();
    }

    private void deleteCompletedCheckListItems() {
        mDataManager.doDeleteAllCheckListCheckedItems(getUserId(), new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                mTodoTabViewButton.performClick();
            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
                showCorrectErrorScreen(error.getErrorCode());

            }

            @Override
            public void onPreExecute() {
               hideAllScreens();
                showProgressBar();
            }
        });
    }

    private void createCheckListItem() {
        if (mCreateCheckListView != null) {
            mCreateCheckListItemInput = (EditText) mCreateCheckListView.findViewById(R.id.checklist_text_input);
            if (mCreateCheckListItemInput.getText().length() <= 0) {
                Toast.makeText(getActivity(),"The CheckList title cant be empty",Toast.LENGTH_SHORT).show();
            } else {
                mDataManager.doCreateCheckListItem(getUserId(), getInput(mCreateCheckListItemInput), new ServiceCallback<BaseResponse>() {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        dismissProgressBar();
                        hideCreateCheckList();
                        showMainScreen();
                        loadCorrectTab();
                    }

                    @Override
                    public void onError(NetworkError error) {
                        dismissProgressBar();
                        hideCreateCheckList();
                        showCorrectErrorScreen(error.getErrorCode());

                    }

                    @Override
                    public void onPreExecute() {
                        hideAllScreens();
                        showProgressBar();
                    }
                });

            }
        }
    }


    protected void hideCreateCheckList() {
        if (mFragmentRoot != null) {
            whiteScreenColor();
            showMainScreen();
            showRightFloatButton();
            mCreateCheckListView.setVisibility(View.GONE);
        }
    }

    private void hideCreateDeleteButton() {
        mCreateCheckListButton.setVisibility(View.GONE);
        mDeleteCheckListButton.setVisibility(View.GONE);
        mRetryButton.setOnClickListener(this);
    }

    private void darkScreenColor() {
        mFragmentRoot.setBackgroundColor(getResources().getColor(R.color.pressed_list_view));
    }

    private void whiteScreenColor() {
        mFragmentRoot.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_checklist_float_button:
                createCheckListItemView();
                break;
            case R.id.delete_checklist_float_button:
                deleteCompletedCheckListItems();
                break;
            case R.id.retry_button:
                 setUp();
                break;
            case R.id.create_checklist_item_button:
                createCheckListItem();
                break;
            case R.id.dismiss_checklist_item_button:
                hideCreateCheckList();
                loadCorrectTab();
                break;
            case R.id.checklist_tab_todo:
                resetSelectedTab();
                setSelectedTab(mTodoTabViewButton);
                showRightFloatButton();
                getTodoCheckList();
                break;
            case R.id.checklist_tab_completed:
                resetSelectedTab();
                setSelectedTab(mCompletedTabViewButton);
                showRightFloatButton();
                getCompletedCheckList();
                break;
            default:
                break;
        }

    }

    public void checkItemOfList(boolean isChecked, int checkListId) {
        mDataManager.doUpdateCheckListState(checkListId, isChecked, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                dismissProgressBar();
                loadCorrectTab();

            }

            @Override
            public void onError(NetworkError error) {
                dismissProgressBar();
               showCorrectErrorScreen(error.getErrorCode());
            }

            @Override
            public void onPreExecute() {
               hideAllScreens();
                showProgressBar();
            }
        });
    }

    public interface CheckListFragmentListener {
        void checkItem(boolean isChecked, int checkListId);
    }
}
