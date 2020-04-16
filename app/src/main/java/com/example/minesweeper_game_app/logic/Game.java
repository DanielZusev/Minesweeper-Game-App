package com.example.minesweeper_game_app.logic;



public class Game {
    private Board mBoard;


    private boolean isFlagged;
    private int boardSize;
    public Boolean end;

    public int cellsToWinCount;

    public Game(int boardSize) {
        this.boardSize=boardSize;
        this.mBoard = new Board(boardSize);
        this.cellsToWinCount = this.boardSize * this.boardSize;
        this.isFlagged = false;
        this.end = null;

    }

    public void OnClick(int pos) {
        Boolean clickValue = mBoard.OnClick(pos, isFlagged);
        if(clickValue==null)
            return;
        //click on mine
        else if (clickValue)
            GameOver();
            //click on UNCOVERED cell
        else {
            cellsToWinCount--;
            if (cellsToWinCount == 0)
                Victory();
        }
    }

    public void ToggleFlagged() {
        if (this.isFlagged)
            this.isFlagged = false;
        else this.isFlagged = true;
    }

    private void Victory() {
        this.end = true;
    }

    public void GameOver() {
        this.end = false;
    }

    public Board getmBoard() {
        return this.mBoard;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

}
