package com.apolloapps.theledger.Features.Bills;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.DateTimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by AMoreira on 6/21/16.
 */
public class BillsListViewItem extends RelativeLayout {

    @Bind(R.id.bill_title)
    TextView mBillTitle;
    @Bind(R.id.bill_due_date)
    TextView mBillDueDate;
    @Bind(R.id.bill_description)
    TextView mBillDescription;
    @Bind(R.id.overflow_menu)
    ImageView mOverflowMenu;

    private Context mContext;
    private OnClickListener mListener;
    private OnLongClickListener mLongListener;

    public BillsListViewItem(Context context) {
        this(context,null,0);
    }

    public BillsListViewItem(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public BillsListViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.view_bill_item,null);
        ButterKnife.bind(this,view);
        addView(view);
    }


    public void setContents(BillModel model, OnClickListener listener, OnLongClickListener longClickListener){
        mBillTitle.setText(model.getBillName());
        mBillDueDate.setText(DateTimeUtils.getLocalizedDateFormat(model.getBillDueDate(),mContext));
        mBillDescription.setText(model.getBillComments());
        mOverflowMenu.setOnClickListener(new OverFlowMenu(mContext,model.getBillId(), AppConstants.FEATURE_TYPE_BILLS));
        mListener = listener;
        mLongListener = longClickListener;
    }

    @OnClick(R.id.background)
    void OnClick(){mListener.onClick(this);}

    @OnLongClick(R.id.background)
    boolean OnLongClick(){ return mLongListener.onLongClick(this);}
}
