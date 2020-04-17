package com.example.minesweeper_game_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class EndActivity extends AppCompatActivity {
    boolean endStatus;
    ImageView imageView;
    int boardSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        imageView = findViewById(R.id.end_screen_image);
        endStatus = getIntent().getBooleanExtra("END_STATUS", false);
        boardSize = getIntent().getIntExtra("BOARD_SIZE", 0);

        if (endStatus)
            setVictory();
        else
            setGameOver();
    }

    public void setVictory() {
        imageView.setImageResource(R.drawable.victory);
    }

    public void setGameOver() {
        imageView.setImageResource(R.drawable.game_over);
    }

    public void endGame(View v) {
        finishAffinity();
        System.exit(0);
    }

    public void startNewGame(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("BOARD_SIZE", boardSize);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("BOARD_SIZE", boardSize);
        startActivity(intent);
    }

}

