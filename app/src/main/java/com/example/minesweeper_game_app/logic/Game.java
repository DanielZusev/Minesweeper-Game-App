package com.example.minesweeper_game_app.logic;


public class Game {

    private Board mBoard;
    private boolean isFlagged;
    private int boardSize;
    public int cellsToWinCount;

    public Game(int boardSize) {
        setBoardSize(boardSize);
        this.mBoard = new Board(this.boardSize);
        this.isFlagged = false;
    }

    public Board getmBoard() {
        return this.mBoard;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void OnClick(int pos) {
        //click on mine
        if (!mBoard.OnClick(pos, isFlagged))
            GameOver();
            //click on UNCOVERED cell
        else if (mBoard.OnClick(pos, isFlagged)) {
            cellsToWinCount--;
        }
        if (cellsToWinCount == 0)
            Victory();
    }

    private void ToggleFlagged() {
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

}
