package com.apolloapps.theledger.About;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.apolloapps.theledger.DataManager.Models.ReferenceModel;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutListItemView extends RelativeLayout {

    @Bind(R.id.reference_title_text)
    TextView mReferenceTitle;
    @Bind(R.id.reference_link_text)
    TextView mReferenceLink;
    @Bind(R.id.reference_description_text)
    TextView mReferenceDescription;

    private OnClickListener mListener;

    public AboutListItemView(Context context) {
        this(context,null,0);
    }

    public AboutListItemView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AboutListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_about_item,null);
        ButterKnife.bind(this,view);
        addView(view);

    }

    public void setContents(ReferenceModel object, OnClickListener listener){
        mReferenceTitle.setText(object.getReferenceTitle());
        mReferenceLink.setText(object.getReferenceLink());
        mReferenceDescription.setText(object.getReferenceDescription());
        mListener = listener;
    }

    @OnClick(R.id.reference_link_text)
    void onClick(){mListener.onClick(this);}
}
