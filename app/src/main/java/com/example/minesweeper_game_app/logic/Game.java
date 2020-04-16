package com.example.minesweeper_game_app.logic;



public class Game {
    private Board mBoard;


    private boolean isFlagged;
    private int boardSize;


    public int cellsToWinCount;

    public Game(int boardSize) {
        this.boardSize=boardSize;
        this.mBoard = new Board(boardSize);
        this.cellsToWinCount = this.boardSize * this.boardSize;
        this.isFlagged = false;

    }

    public Boolean OnClick(int pos) {
        Boolean clickValue = mBoard.OnClick(pos, isFlagged);
        if(clickValue==null)
            return null;
        //click on mine
        else if (clickValue)
            return true;
            //click on UNCOVERED cell
        else {
            cellsToWinCount--;
            if (cellsToWinCount == 0)
                return false;
        }
        return null;
    }

    public void ToggleFlagged() {
        if (this.isFlagged)
            this.isFlagged = false;
        else this.isFlagged = true;
    }

    public Board getmBoard() {
        return this.mBoard;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

}
