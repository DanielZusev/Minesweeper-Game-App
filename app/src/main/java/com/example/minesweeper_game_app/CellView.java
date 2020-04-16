package com.example.minesweeper_game_app;

import android.content.Context;
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
                break;
            case FLAGGED:
                mImageView.setImageResource(R.drawable.flagged);
                break;
            case COVERED:
                mImageView.setImageResource(R.drawable.zero);
                break;
            case ONE:
                mImageView.setImageResource(R.drawable.one);
                break;
            case TWO:
                mImageView.setImageResource(R.drawable.two);
                break;
            case THREE:
                mImageView.setImageResource(R.drawable.three);
                break;
            case FOUR:

                mImageView.setImageResource(R.drawable.four);
                break;
            case MINE:
                mImageView.setImageResource(R.drawable.bomb);
                break;
        }
    }
}
