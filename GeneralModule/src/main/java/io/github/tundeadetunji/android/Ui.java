package io.github.tundeadetunji.android;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Ui {

    public static void bindProperty(Context context, AutoCompleteTextView control, String[] list, int layout){
        control.setAdapter(new ArrayAdapter<>(context, layout, list));
    }

    public static void bindProperty(Context context, AutoCompleteTextView control, String[] list, int layout, String initialText){
        control.setText(String.valueOf(initialText));
        control.setAdapter(new ArrayAdapter<>(context, layout, list));
    }

}
