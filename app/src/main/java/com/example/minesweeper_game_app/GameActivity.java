package com.example.minesweeper_game_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minesweeper_game_app.logic.CellAdapter;
import com.example.minesweeper_game_app.logic.Game;

import static com.example.minesweeper_game_app.R.id.gridView;

public class GameActivity extends AppCompatActivity {


    Game mGame;
    GridView mGridView;
    CellAdapter mCellAdapter;
    TextView mPlayerTurnTextView;
    int boardSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boardSize = getIntent().getIntExtra("BOARD_SIZE", 0);
        mGame = new Game(boardSize);

        mGridView = findViewById(gridView);
        mGridView.setNumColumns(boardSize);
        mCellAdapter = new CellAdapter(this, mGame.getmBoard());
        mGridView.setAdapter(mCellAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mGame.OnClick(position);
                mCellAdapter.notifyDataSetChanged();

            }
        });

    }
}
