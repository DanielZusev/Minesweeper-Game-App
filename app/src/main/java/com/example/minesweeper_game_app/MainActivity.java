package com.example.minesweeper_game_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private static final int EASY = 5;
    private static final int HARD = 6;
    private static final int EXTREME = 7;
    private Integer choose = null;
    private SharedPreferences difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.start_button);
        this.difficulty = getSharedPreferences("DB", MODE_PRIVATE);
        if (difficulty.getBoolean("FirstStart",false)) {
            onStartLevel();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose != null) {
                    SharedPreferences.Editor editor = difficulty.edit();
                    editor.putInt("level",choose);
                    editor.putBoolean("FirstStart", true);
                    editor.apply();
                    openGameActivity(choose);
                }
            }
        });

    }

    public void openGameActivity(int boardSize) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("BOARD_SIZE", boardSize);
        startActivity(intent);
    }

    public void CHOOSE_EASY(View v) {
        choose = EASY;
        v.setBackground(getDrawable(R.drawable.easy_button_shape_pressed));
        findViewById(R.id.hard_button).setBackground(getDrawable(R.drawable.hard_button_shape));
        findViewById(R.id.extreme_button).setBackground(getDrawable(R.drawable.extreme_button_shape));

    }

    public void CHOOSE_HARD(View v) {
        choose = HARD;
        v.setBackground(getDrawable(R.drawable.hard_button_shape_pressed));
        findViewById(R.id.easy_button).setBackground(getDrawable(R.drawable.easy_button_shape));
        findViewById(R.id.extreme_button).setBackground(getDrawable(R.drawable.extreme_button_shape));
    }

    public void CHOOSE_EXTREME(View v) {
        choose = EXTREME;
        v.setBackground(getDrawable(R.drawable.extreme_button_shape_pressed));
        findViewById(R.id.hard_button).setBackground(getDrawable(R.drawable.hard_button_shape));
        findViewById(R.id.easy_button).setBackground(getDrawable(R.drawable.easy_button_shape));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

    public void onStartLevel(){
        SharedPreferences difficulty = getSharedPreferences("DB",MODE_PRIVATE);
        choose = difficulty.getInt("level",5);
        View view;
        switch (choose){
            case EASY: view = findViewById(R.id.easy_button); CHOOSE_EASY(view); break;
            case HARD: view = findViewById(R.id.hard_button); CHOOSE_HARD(view); break;
            case EXTREME: view = findViewById(R.id.extreme_button); CHOOSE_EXTREME(view); break;
        }
    }
}
