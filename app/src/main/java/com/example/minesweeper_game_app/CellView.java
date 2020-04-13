package com.example.minesweeper_game_app;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CellView extends LinearLayout {
    TextView mTextView;
    public CellView(Context context) {
        super(context);


        mTextView = new TextView(context);

        LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setLayoutParams(layoutParams);
        mTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(50);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setText("DEFAULT");
        setBackgroundColor(Color.DKGRAY);

        addView(mTextView);

    }

    public void setText(CharSequence charSequence) {

        mTextView.setText(charSequence);

    }
}
