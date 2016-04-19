package com.apolloapps.theledger;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by AMoreira on 4/4/16.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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

}
