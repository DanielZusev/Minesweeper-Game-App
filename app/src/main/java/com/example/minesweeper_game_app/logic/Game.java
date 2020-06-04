package com.example.minesweeper_game_app.logic;



public class Game {
    private Board mBoard;


    private boolean isFlagged;
    private int boardSize;


    public Game(int boardSize,int numOfMines) {
        this.boardSize=boardSize;

        this.mBoard = new Board(boardSize,numOfMines);

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
        else{
            if (mBoard.getCellsToWinCount() == 0)
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

    public void revealBoard(){
        for (int i = 0; i < mBoard.getBoardSize(); i++) {
            if (mBoard.getCell(i).isMined()&&mBoard.getCell(i).getmState()!= Cell.State.BOOM){
                mBoard.getCell(i).setState(Cell.State.MINE);
            }
        }
    }

    public boolean sensorIsActive(){
        //TODO game over

        return this.mBoard.sensorIsActive();
        //if(this.mBoard.getBoardSize()*this.mBoard.getBoardSize() - this.mBoard.getCellsToWinCount())
    }

}
