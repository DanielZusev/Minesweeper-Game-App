package com.example.minesweeper_game_app;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minesweeper_game_app.logic.Cell;

public class CellView extends LinearLayout {
    TextView mTextView;
    ImageView mImageView;
    public CellView(Context context) {
        super(context);

        mImageView = new ImageView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        /*mTextView.setLayoutParams(layoutParams);
        mTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(50);
        mTextView.setTextColor(Color.WHITE);
        setBackgroundColor(Color.DKGRAY);*/

        addView(mImageView);

    }

    public void setText(Cell.State state) {
        switch (state){
            case UNCOVERED:
                mImageView.setImageResource(R.drawable.facing_down);
            case FLAGGED:
                mImageView.setImageResource(R.drawable.flagged);


        }


    }
}
