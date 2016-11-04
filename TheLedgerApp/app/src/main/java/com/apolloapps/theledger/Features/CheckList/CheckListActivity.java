package com.apolloapps.theledger.Features.CheckList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/21/16.
 */
public class CheckListActivity extends BaseActivity implements CheckListFragment.CheckListFragmentListener {


    private CheckListFragment mCheckListFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        if (getIntent().getExtras() == null) {
            createCheckListFragment();
        } else {
            createCheckListFragmentFromFloat();
        }
    }


    private void createCheckListFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, CheckListFragment.newInstance(), null).commit();
    }

    private void createCheckListFragmentFromFloat(){
        mCheckListFragment = CheckListFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, mCheckListFragment, null).commit();

    }


    @Override
    public void checkItem(boolean isChecked, int checkListId) {
       final CheckListFragment fragment = (CheckListFragment) getFragmentManager().findFragmentById(R.id.container);
        fragment.checkItemOfList(isChecked,checkListId);
    }
}

