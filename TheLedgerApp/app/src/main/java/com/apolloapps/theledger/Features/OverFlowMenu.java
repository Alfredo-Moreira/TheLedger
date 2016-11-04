package com.apolloapps.theledger.Features;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/25/16.
 */
public class OverFlowMenu implements View.OnClickListener {

    private Context mContext;
    private int mFeatureID;
    private int mFeatureType;
    private OverFlowMenuListener mListener;


    public OverFlowMenu (Context context, int featureID,int featureType) {
        mContext = context;
        mFeatureID = featureID;
        mFeatureType = featureType;
        if(mContext instanceof OverFlowMenuListener) {
            mListener = (OverFlowMenuListener) mContext;
        } else {
            throw new RuntimeException(mContext.getString(R.string.listener_not_implemented));
        }
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(mContext,v);
        popupMenu.inflate(R.menu.menu_feature_options);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        mListener.editFeature(mFeatureType,mFeatureID);
                        return true;
                    case R.id.action_delete:
                        mListener.deleteFeature(mFeatureType,mFeatureID);
                        return true;
                    case R.id.action_view:
                        mListener.viewFeature(mFeatureType, mFeatureID);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }


    public interface OverFlowMenuListener {
        void viewFeature(int featureType, int accountID);
        void editFeature(int featureType, int accountID);
        void deleteFeature(int featureType, int accountID);
    }
}
