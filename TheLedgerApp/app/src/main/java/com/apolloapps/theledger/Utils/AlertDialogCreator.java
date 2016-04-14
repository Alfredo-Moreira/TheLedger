package com.apolloapps.theledger.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/8/16.
 */
public class AlertDialogCreator {

    public static AlertDialog showDefaultDialog(Context context, String title,
                                                String message, DialogInterface.OnClickListener rightClickListener,
                                                DialogInterface.OnClickListener leftClickListener, DialogInterface.OnCancelListener cancelListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if (rightClickListener != null) {
            builder.setPositiveButton(context.getText(R.string.dialog_ok), rightClickListener);
        }
        if (leftClickListener != null) {
            builder.setNegativeButton(context.getText(R.string.dialog_cancel), leftClickListener);
        }
        if (cancelListener != null) {
            builder.setOnCancelListener(cancelListener);
        } else {
            builder.setCancelable(false);
        }
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static AlertDialog showCustomDialog(Context context, String title,
                                               String message, DialogInterface.OnClickListener rightClickListener, String rightButtonText,
                                               DialogInterface.OnClickListener leftClickListener, String leftButtonText,
                                               DialogInterface.OnCancelListener cancelListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if (rightClickListener != null) {
            builder.setPositiveButton(rightButtonText, rightClickListener);
        }
        if (leftClickListener != null) {
            builder.setNegativeButton(leftButtonText, leftClickListener);
        }
        if (cancelListener != null) {
            builder.setOnCancelListener(cancelListener);
        } else {
            builder.setCancelable(false);
        }
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }


}
