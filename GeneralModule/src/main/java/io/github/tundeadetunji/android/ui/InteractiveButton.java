package io.github.tundeadetunji.android.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

import io.github.tundeadetunji.android.R;

public class InteractiveButton extends RelativeLayout {

    private MaterialButton button;
    private ProgressBar progressBar;

    public InteractiveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractiveButton(Context context, String text, OnClickListener clickListener, boolean useWithProgressBar) {
        super(context);
        init(context, text, null, clickListener, useWithProgressBar);
    }

    public InteractiveButton(Context context, String text, Drawable icon, OnClickListener clickListener, boolean useWithProgressBar) {
        super(context);
        init(context, text, icon, clickListener, useWithProgressBar);
    }

    private void init(Context context, String text, Drawable icon, OnClickListener clickListener, boolean useWithProgressBar) {
        // Inflate the layout
        inflate(context, R.layout.interactive_button_layout, this);

        button = findViewById(R.id.interactive_button);
        progressBar = findViewById(R.id.placeholder_progress_bar);

        button.setText(text);
        if (icon != null) button.setIcon(icon);
        button.setOnClickListener(v -> {
            if (useWithProgressBar) {
                showProgressBar();
            }
            clickListener.onClick(v);
            if (useWithProgressBar) {
                // Simulate a long-running task
                postDelayed(this::hideProgressBar, 3000); // Change 2000 to your task duration
            }
        });
    }

    private void showProgressBar() {
        button.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    private void hideProgressBar() {
        button.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }
}
