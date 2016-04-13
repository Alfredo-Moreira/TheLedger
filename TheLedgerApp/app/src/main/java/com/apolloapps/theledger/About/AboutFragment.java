package com.apolloapps.theledger.About;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Common.References;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.donate_button)
    Button mDonate;
    @Bind(R.id.about_list)
    RecyclerView mAboutList;

    private AboutFragmentListener mListener;
    private AboutAdapter mAdapter;
    private References mReferences;

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof AboutFragmentListener){
            mListener = (AboutFragmentListener) activity;
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_about,container,false);
        ButterKnife.bind(this,view);
        setUp();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donate_button:
                mListener.donate();
                break;
            default:
                break;
        }
    }
    private void setUp(){
        mDonate.setOnClickListener(this);
        mReferences = new References(getActivity());
        mAdapter = new AboutAdapter(mReferences,mListener);
        mAboutList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAboutList.setAdapter(mAdapter);
        mAboutList.setHasFixedSize(true);


    }

    public interface AboutFragmentListener {
        void donate();
        void followLink(String link);
    }
}
