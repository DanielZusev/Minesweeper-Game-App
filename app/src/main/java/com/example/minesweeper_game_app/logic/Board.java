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
       int minesAround=CheckHowManyMinesAround(pos);

       Boolean isMined=mCells[pos].OnClick(isFlagged,minesAround);
       if(!isMined&&minesAround==0){
        OnClickAround(pos);
       }
       return isMined;
    }
    private void OnClickAround(int pos){
        OnClick(pos+1,false);
        OnClick(pos-1,false);
        OnClick(pos+1+this.mCells.length,false);
        OnClick(pos+this.mCells.length,false);
        OnClick(pos-this.mCells.length,false);
        OnClick(pos+1-this.mCells.length,false);
        OnClick(pos-1-this.mCells.length,false);
        OnClick(pos-1+this.mCells.length,false);

    }
    public int getBoardSize(){
        return this.mCells.length;
    }
    public Cell getCell(int pos){
        return mCells[pos];
    }
    private int CheckHowManyMinesAround(int pos) {
        int minesCount = 0;
        int boardSize = mCells.length;
        //cell at first col
        if (mCells[pos + 1].isMined())
            minesCount++;
        if (mCells[pos + boardSize].isMined())
            minesCount++;
        if (mCells[pos + boardSize + 1].isMined())
            minesCount++;
        if (mCells[pos - 1].isMined())
            minesCount++;
        if (mCells[pos - boardSize].isMined())
            minesCount++;
        if (mCells[pos - boardSize - 1].isMined())
            minesCount++;
        if (mCells[pos - boardSize + 1].isMined())
            minesCount++;
        if (mCells[pos + boardSize - 1].isMined())
            minesCount++;
        return minesCount;
    }
}
