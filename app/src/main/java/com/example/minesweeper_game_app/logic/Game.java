package com.example.minesweeper_game_app.logic;

public class Game {
    private Board mBoard;


    private boolean isFlagged;
    private int boardSize;

    public int cellsToWinCount;

    public Game(int boardSize) {
        this.boardSize=boardSize;
        this.mBoard = new Board(boardSize);

        this.isFlagged = false;


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
        //TODO victory logic
    }

    public void GameOver() {
        //TODO GameOver logic
    }

    public Board getmBoard() {
        return this.mBoard;
    }

    public boolean isFlagged() {
        return isFlagged;
    }


}
