package com.apolloapps.theledger.Settings;

import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.R;

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
        setToolBarTitle(getString(R.string.donate));
    }
}
