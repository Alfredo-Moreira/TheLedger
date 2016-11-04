package com.apolloapps.theledger.Features.CheckList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apolloapps.theledger.DataManager.Models.CheckListModel;
import com.apolloapps.theledger.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by AMoreira on 8/23/16.
 */
public class CheckListItem extends RelativeLayout{

    private Context mContext;
    private CompoundButton.OnCheckedChangeListener mCheckedListener;
    private OnLongClickListener mLongClickListener;

    @Bind(R.id.checklist_text)
    TextView mCheckListTitle;
    @Bind(R.id.checklist_checkbox)
    CheckBox mCheckBox;
    @Bind(R.id.background)
    RelativeLayout mListItemView;



    public CheckListItem(Context context) {
        this(context,null,0);
    }

    public CheckListItem(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CheckListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext =context;
        ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.view_checklist_item,null);
        ButterKnife.bind(this,view);
        addView(view);
    }


    public void setContents(CheckListModel model, CompoundButton.OnCheckedChangeListener checkedChangeListener, OnLongClickListener longClickListener){

            mCheckListTitle.setText(model.getCheckListTitle());
            mCheckBox.setOnCheckedChangeListener(null);
            mLongClickListener = longClickListener;
            mCheckBox.setChecked(model.isChecked());
            //hack to make it work for now
            mCheckedListener = checkedChangeListener;
            mCheckBox.setOnCheckedChangeListener(mCheckedListener);


    }

    @OnLongClick(R.id.background)
    boolean OnLongClick(){ return mLongClickListener.onLongClick(this);}

}
