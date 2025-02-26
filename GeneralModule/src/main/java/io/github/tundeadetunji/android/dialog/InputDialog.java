package io.github.tundeadetunji.android.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.IOException;

public abstract class InputDialog {
    private String title = "";
    private String message = "";
    private String hint = "Enter text here...";
    private String positiveButtonText = "OK";
    private String negativeButtonText = "Cancel";
    private boolean negativeButton = true;
    private Context context;
    private final EditText text;
    private LinearLayout layout;
    private AlertDialog.Builder builder;


    public String getText(){
        return text != null ? text.getText().toString() : "";
    }

    public InputDialog(Context context, String title, String message, String hint){
        this.title = title;
        this.message = message;
        this.hint = hint;
        this.context = context;
        text  = new EditText(context);
        buildDialog(context);
    }

    public InputDialog(Context context, String title, String message, String hint, String positiveButtonText, String negativeButtonText, boolean showNegativeButton){
        this.title = title;
        this.message = message;
        this.hint = hint;
        this.context = context;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText=negativeButtonText;
        this.negativeButton = showNegativeButton;
        text  = new EditText(context);
        buildDialog(context);
    }

    public abstract void positiveButtonAction() throws IOException;
    public abstract void negativeButtonAction();

    public void show() {
        builder.create().show();
    }
    public void showNegativeButton(boolean value){
        negativeButton = value;
    }

    private AlertDialog.Builder buildDialog(Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(8,8,8,8);
        text.setHint(hint);
        layout.addView(text);
        builder.setView(layout);

        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    positiveButtonAction();
                } catch (IOException ignored) {

                }
            }
        });
        if (negativeButton)
            builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    negativeButtonAction();
                }
            });
        return builder;
    }

}
