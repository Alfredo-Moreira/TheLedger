package com.apolloapps.theledger.About;

import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.BaseFragment;

/**
 * Created by AMoreira on 4/13/16.
 */
public class DonateFragment extends BaseFragment {
    public static DonateFragment newInstance() {
        return new DonateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).setToolBarTitle("Donate");
    }
}
