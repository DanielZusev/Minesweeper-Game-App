package com.example.minesweeper_game_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private static final int EASY=4;
    private static final int HARD=6;
    private static final int EXTREME=8;
    private Integer choose=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose!=null){
                    openGameActivity(choose);
                }
            }
        });

    }

    public void openGameActivity(int boardSize) {
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("BOARD_SIZE",boardSize);
        startActivity(intent);
    }
    public void CHOOSE_EASY(View v) {
        choose=EASY;
        v.setBackgroundColor(Color.GRAY);

    }
    public void CHOOSE_HARD(View v) {
        choose=HARD;
        v.setBackgroundColor(Color.GRAY);
    }
    public void CHOOSE_EXTREME(View v) {
        choose=EXTREME;
        v.setBackgroundColor(Color.GRAY);
    }
}
