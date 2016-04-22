package com.apolloapps.theledger;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import butterknife.Bind;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseFragment extends Fragment {

    @Bind(R.id.fragment_root)
    @Nullable
    protected View mRootView;
    private FloatingActionButton mFloatingActionButton;
    private SpringFloatingActionMenu mSpringFloatActionMenu;
    protected ProgressBar mProgressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getStringResource(int id) {
        return getString(id);
    }

    public String getInput(EditText input) {
        return input.getText().toString().trim();
    }

    public int getIntResource(int id) {
        return getResources().getInteger(id);
    }

    public boolean isInvalidInput(String input) {
        if (input.isEmpty()) {
            return true;
        }
        return false;
    }

    public void setSelectedMenuState(int id) {
        RelativeLayout menu = (RelativeLayout) getActivity().findViewById(id);
        menu.setBackgroundColor(getResources().getColor(R.color.menu_selected_state));
        ((BaseActivity) getActivity()).setSelectedMenu(id);
    }

    public void showProgressBar() {
        ViewGroup layout = (ViewGroup) mRootView.findViewById(R.id.fragment_root);
        if (layout != null) {
            mProgressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
            mProgressBar.setIndeterminate(true);
            mProgressBar.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            layout.addView(mProgressBar, params);
        }
    }

    public void dismissProgressBar() {
        if (mProgressBar != null && mProgressBar.isShown()) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public void setToolBarTitle(String title) {
        ((BaseActivity)getActivity()).setToolBarTitle(title);
    }

    private FloatingActionButton setUpFloatingButton(Context context, RecyclerView recyclerView) {
        mFloatingActionButton = new FloatingActionButton(context);
        mFloatingActionButton.setType(FloatingActionButton.TYPE_NORMAL);
        mFloatingActionButton.setImageResource(R.drawable.ic_create_white_48dp);
        mFloatingActionButton.attachToRecyclerView(recyclerView);
        mFloatingActionButton.setShadow(true);
        return mFloatingActionButton;
    }

    public void setUpFloatingAddButton(final Context context, RecyclerView recyclerView, View.OnClickListener accountListener, View.OnClickListener billsListener,
                                       View.OnClickListener checkListListener) {
        mSpringFloatActionMenu = new SpringFloatingActionMenu.Builder(context)
                .fab(setUpFloatingButton(context, recyclerView))
                .addMenuItem(R.color.colorPrimary, R.drawable.account_drawable,getStringResource(R.string.account_feature), R.color.white, accountListener)
                .addMenuItem(R.color.colorPrimary, R.drawable.ic_credit_card_black_24dp, getStringResource(R.string.bills_feature), R.color.white, billsListener)
                .addMenuItem(R.color.colorPrimary, R.drawable.chrck_list_drawable,getStringResource(R.string.check_list_feature), R.color.white,checkListListener)
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_BLOOM)
                .revealColor(R.color.colorAccent).onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        mFloatingActionButton.setImageResource(R.drawable.ic_clear_white_48dp);

                    }

                    @Override
                    public void onMenuClose() {
                      mFloatingActionButton.setImageResource(R.drawable.ic_create_white_48dp);
                    }
                })
                .build();

    }

    public boolean isFloatMenuOpen(){
        return mSpringFloatActionMenu.isMenuOpen();
    }

    public void closeFloatButton(){
        mSpringFloatActionMenu.hideMenu();
    }

}
