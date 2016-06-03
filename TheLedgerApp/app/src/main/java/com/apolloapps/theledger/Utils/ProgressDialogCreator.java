package com.apolloapps.theledger.Utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 5/11/16.
 */
public class ProgressDialogCreator {

    public static ProgressDialog showProgressDialog(Context context) {

        return ProgressDialog.show(context, context.getString(R.string.please_wait), context.getString(R.string.processing), true);
    }

    public static void dismissDialog(ProgressDialog dialog) {
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}