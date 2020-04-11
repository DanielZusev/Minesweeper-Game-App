package com.example.minesweeper_game_app.logic;

import java.time.Clock;
import java.util.Timer;

public class Game {
    private Board mBoard;
    private boolean isFlagged;
    private int boardSize;
    private Timer timer;
    public int cellsToWinCount;
    public Game(int boardSize){
        this.mBoard=new Board(boardSize);
        this.isFlagged=false;
        this.timer=new Timer();
        StartTimer();
    }
    public void OnClick(int pos){
        //click on mine
        if(!mBoard.OnClick(pos,isFlagged))
            GameOver();
        //click on UNCOVERED cell
        else if(mBoard.OnClick(pos,isFlagged)){
            cellsToWinCount--;
            if(cellsToWinCount==0)
                Victory();
        }
            
    }
    private void ToggleFlagged(){
        if(this.isFlagged)
            this.isFlagged=false;
        else this.isFlagged=true;
    }
    private void Victory() {
        StopTimer();
        //TODO victory logic
    }

    public void GameOver(){
        StopTimer();
        //TODO GameOver logic
    }

    private void StartTimer(){

    }
    private void StopTimer(){

    }
}
