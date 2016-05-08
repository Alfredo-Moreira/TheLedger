package com.apolloapps.theledger.Dashboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/6/16.
 */
public class DashboardFragment extends BaseFragment {

    @Bind(R.id.dashboard_list_view)
    RecyclerView mDashboardList;

    @Bind(R.id.floating_menu_button)
    FloatingActionMenu mFloatingActionMenu;

    @Bind(R.id.floating_menu_account_item)
    FloatingActionButton mFloatingAccountButton;

    @Bind(R.id.floating_menu_bill_item)
    FloatingActionButton mFlotatingBillButton;

    @Bind(R.id.floating_menu_checklist_item)
    FloatingActionButton mFloatingCheckListButton;


    private DashBoardFragmentListener mListener;
    private DashboardAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof DashBoardFragmentListener) {
            mListener = (DashBoardFragmentListener) activity;
        } else {
            throw new RuntimeException(getStringResource(R.string.listener_not_implemented));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, viewGroup);
        setUp(getActivity());
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


    private void setUp(Context context) {
        mAdapter = new DashboardAdapter(context, mListener);
        mLayoutManager = new LinearLayoutManager(context);
        mDashboardList.setAdapter(mAdapter);
        mDashboardList.setLayoutManager(mLayoutManager);
        mDashboardList.setHasFixedSize(true);
        setSelectedMenuState(R.id.lower_menu_home);
        setClickListeners();
    }

    private void setClickListeners() {
        mFloatingAccountButton.setOnClickListener(addButtonAccountListener());
        mFlotatingBillButton.setOnClickListener(addButtonBillsListener());
        mFloatingCheckListButton.setOnClickListener(addButtonCheckListListener());
    }

    public interface DashBoardFragmentListener {
        void startFeatureCreate(String classPath,String action, int value);
        void startFeature(String classPath);
    }

    public boolean isFloatingMenuOpen() {
        return mFloatingActionMenu.isOpened();
    }

    public void closeFloatingMenu(boolean animation) {
        mFloatingActionMenu.close(animation);
    }

    private View.OnClickListener addButtonAccountListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to Account
                mListener.startFeatureCreate(getStringResource(R.string.account_class_path),"Create Account",1);
            }
        };
    }

    private View.OnClickListener addButtonBillsListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to Account
                Toast.makeText(getActivity(),getStringResource(R.string.bills_feature),Toast.LENGTH_SHORT).show();
            }
        };
    }
    private View.OnClickListener addButtonCheckListListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to Account
                Toast.makeText(getActivity(),getStringResource(R.string.check_list_feature),Toast.LENGTH_SHORT).show();
            }
        };
    }


}
