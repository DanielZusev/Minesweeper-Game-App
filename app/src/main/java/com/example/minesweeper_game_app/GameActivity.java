package com.example.minesweeper_game_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minesweeper_game_app.logic.CellAdapter;
import com.example.minesweeper_game_app.logic.Game;

public class GameActivity extends AppCompatActivity {


    Game mGame;
    GridView mGridView;
    Button restartButton;
    ImageView flagButton;
    CellAdapter mCellAdapter;
    TextView mPlayerTurnTextView;
    Chronometer timer;

    int boardSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);

        boardSize=getIntent().getIntExtra("BOARD_SIZE",0);
        mGame = new Game(boardSize);

        mGridView = findViewById(R.id.gridView);
        restartButton =findViewById(R.id.restart_button);
        flagButton =(ImageView)findViewById(R.id.flag_button);

        mGridView.setNumColumns(boardSize);
        mCellAdapter = new CellAdapter(this, mGame.getmBoard());
        mGridView.setAdapter(mCellAdapter);

        timer = findViewById(R.id.chronometer);
        timer.setFormat("Time: %s");
        timer.start();
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mGame.getmBoard().CreateNewBoard(boardSize);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mGame.OnClick(position);
                mCellAdapter.notifyDataSetChanged();

            }
        });
        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGame.ToggleFlagged();
                if(mGame.isFlagged())
                    flagButton.setImageResource(R.drawable.flag_on);
                else
                    flagButton.setImageResource(R.drawable.flagged);
            }
        });
    }
}
