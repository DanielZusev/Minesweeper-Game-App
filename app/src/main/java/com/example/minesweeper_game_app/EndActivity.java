package com.example.minesweeper_game_app;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class EndActivity extends AppCompatActivity {
    boolean endStatus;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        imageView=findViewById(R.id.end_screen_image);
        endStatus = getIntent().getBooleanExtra("END_STATUS", false);

        if (endStatus)
            setVictory();
        else
            setGameOver();
    }

    public void setVictory(){
        imageView.setImageResource(R.drawable.victory);
    }

    public void setGameOver(){
        imageView.setImageResource(R.drawable.game_over);}
}
