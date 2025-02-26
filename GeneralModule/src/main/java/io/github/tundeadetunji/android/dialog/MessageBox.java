package io.github.tundeadetunji.android.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public abstract class MessageBox {
    private String title = "";
    private String message = "Proceed?";
    private String positiveButtonText = "Yes";
    private String negativeButtonText = "No";
    private boolean showNegativeButton = false;

    public MessageBox(String title, String message, String positiveButtonText, String negativeButtonText, boolean showNegativeButton){
        this.title = title;
        this.message = message;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonText;
        this.showNegativeButton = showNegativeButton;
    }

    public MessageBox(String title, String message){
        this.title = title;
        this.message = message;
    }

    public abstract void positiveButtonAction();
    public abstract void negativeButtonAction();

    public void show(Context context) {
        buildDialog(context).create().show();
    }

    private AlertDialog.Builder buildDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positiveButtonAction();
            }
        });
        if (showNegativeButton)
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    negativeButtonAction();
                }
            });
        return builder;
    }

}
