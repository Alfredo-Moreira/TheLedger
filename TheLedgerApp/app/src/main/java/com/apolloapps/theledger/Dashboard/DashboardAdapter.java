package com.apolloapps.theledger.Dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.Dashboard.Features.FeatureBaseClass;
import com.apolloapps.theledger.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AMoreira on 4/8/16.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    private List<FeatureBaseClass> mAvailableFeatures = new ArrayList<>();
    private DashboardFragment.DashBoardFragmentListener mListener;

    public DashboardAdapter(Context context, DashboardFragment.DashBoardFragmentListener listener) {

        String[] features = context.getResources().getStringArray(R.array.available_features);
        for (String feature : features) {
            try {
                Class<?> clazz = Class.forName(feature);
                FeatureBaseClass object = (FeatureBaseClass) clazz.newInstance();

                object.init(context);

                mAvailableFeatures.add(object);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException ie) {
                ie.printStackTrace();
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            }
        }

        mListener = listener;

    }


    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DashboardListItemView view = new DashboardListItemView(parent.getContext());
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, int position) {
        holder.mDashboardListItemView.setContents(mAvailableFeatures.get(position), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardListItemView view = (DashboardListItemView) v;
                mListener.startFeature(view.mFeature.getFeatureClass());
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mAvailableFeatures.isEmpty()) {
            return 0;
        }
        return mAvailableFeatures.size();
    }
}
