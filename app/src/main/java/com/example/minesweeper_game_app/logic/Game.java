package com.example.minesweeper_game_app.logic;
public class Game {
    private Board mBoard;


    private boolean isFlagged;
    private int boardSize;

    public int cellsToWinCount;

    public Game(int boardSize){
        this.mBoard=new Board(boardSize);
        this.isFlagged=false;


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
    public void ToggleFlagged(){
        if(this.isFlagged)
            this.isFlagged=false;
        else this.isFlagged=true;
    }
    private void Victory() {
        //TODO victory logic
    }

    public void GameOver(){
        //TODO GameOver logic
    }

    public Board getmBoard() {
        return this.mBoard;
    }
    public boolean isFlagged() {
        return isFlagged;
    }


}
