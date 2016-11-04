package com.apolloapps.theledger.Dashboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.Dashboard.FeaturesList.FeatureBaseClass;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AMoreira on 4/8/16.
 */

public class DashboardListItemView extends RelativeLayout {


    FeatureBaseClass mFeature;

    @Bind(R.id.feature_image_icon)
    ImageView mFeatureIcon;

    @Bind(R.id.feature_name_text)
    TextView mFeatureName;


    private OnClickListener mListener;

    public DashboardListItemView(Context context) {
        this(context, null, 0);
    }

    public DashboardListItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_dashboard_item, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setContents(FeatureBaseClass feature, OnClickListener clickListener) {
        mFeature = feature;
        mFeatureIcon.setImageDrawable(getResources().getDrawable(feature.getFeatureDrawableId()));
        mFeatureName.setText(feature.getFeatureName());
        mListener = clickListener;
    }

    @OnClick(R.id.list_cell_container)
    void onClick() {
        mListener.onClick(this);
    }
}
