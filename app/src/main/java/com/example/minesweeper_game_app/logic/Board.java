package com.example.minesweeper_game_app.logic;

import java.util.Random;

public class Board {
    private Cell[] mCells;

    public Board(int mSize){
        this.mCells = new Cell[mSize];
        CreateNewBoard(mSize);
    }
    public void CreateNewBoard(int mSize){
        //create cells
        for(int i=0;i<mSize*mSize;i++)
            this.mCells[i] = new Cell();
        //put mines at random positions
        Random r =new Random();
        int minePos;
        for(int i=0;i<mSize;i++){
            minePos=r.nextInt(mSize);
            if(mCells[minePos].isMined())
                i--;
            else
                mCells[minePos].setMined(true);
        }
    }

    public Boolean OnClick(int pos,boolean isFlagged){
       return mCells[pos].OnClick(isFlagged);
    }
    public int getBoardSize(){
        return this.mCells.length;
    }
    public Cell getCell(int pos){
        return mCells[pos];
    }
}
