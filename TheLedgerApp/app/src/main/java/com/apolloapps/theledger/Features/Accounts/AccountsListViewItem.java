package com.apolloapps.theledger.Features.Accounts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by AMoreira on 4/25/16.
 */
public class AccountsListViewItem extends RelativeLayout {

    @Bind(R.id.account_title)
    TextView mAccountTitle;

    @Bind(R.id.account_description)
    TextView mAccountDescription;

    @Bind(R.id.overflow_menu)
    ImageView mOverFlowMenu;

    private OnClickListener mListener;
    private OnLongClickListener mLongListener;
    private Context mContext;

    public AccountsListViewItem(Context context) {
        this(context,null,0);
    }

    public AccountsListViewItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AccountsListViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_account_item,null);
        mContext = context;
        ButterKnife.bind(this,view);
        addView(view);
    }


    public void setContents(Context context, AccountModel account, OnClickListener listener , OnLongClickListener longListener) {

        mAccountTitle.setText(account.getAccountTitle());
        if(account.getAccountComments()!=null) {
            mAccountDescription.setText(account.getAccountComments());
        } else {
            mAccountDescription.setText(mContext.getString(R.string.no_decription));
        }
        mOverFlowMenu.setOnClickListener(new OverFlowMenu(context,account.getAccountId(), AppConstants.FEATURE_TYPE_ACCOUNT));
        mListener = listener;
        mLongListener = longListener;
    }

    @OnClick(R.id.background)
    void onClick(){mListener.onClick(this);}

    @OnLongClick(R.id.background)
    boolean onLongClick(){ return mLongListener.onLongClick(this);}
}
