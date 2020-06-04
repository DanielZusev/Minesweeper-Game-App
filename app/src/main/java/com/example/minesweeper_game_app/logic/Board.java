package com.example.minesweeper_game_app.logic;

import android.util.Log;

import java.util.Random;

public class Board {
    private Cell[] mCells;
    private int mSize;
    private int cellsToWinCount;
    private int numOfMines;

    public Board(int mSize,int numOfMines) {

        setmSize(mSize);
        this.numOfMines = numOfMines;
        this.mCells = new Cell[this.mSize * this.mSize];
        CreateNewBoard(this.numOfMines);
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public void CreateNewBoard(int numOfMines) {
        this.numOfMines = numOfMines;
        this.cellsToWinCount = (this.mSize*this.mSize)-this.numOfMines;
        //create cells
        for (int i = 0; i < this.mSize * this.mSize; i++)
            this.mCells[i] = new Cell();
        //put mines at random positions
        Random r = new Random();
        int minePos;

        for (int i = 0; i < this.numOfMines; i++) {
            minePos = r.nextInt(this.mSize * this.mSize);

            if (mCells[minePos].isMined())
                i--;
            else {
                mCells[minePos].setMined(true);
                Log.e("myTag", ","+minePos);
            }
        }
    }



    public Boolean OnClick(int pos, boolean isFlagged) {
        if(this.mCells[pos].getmState()!= Cell.State.UNCOVERED&&this.mCells[pos].getmState()!= Cell.State.FLAGGED)
            return null;
        int minesAround = CheckHowManyMinesAround(pos);

        Boolean isMined = mCells[pos].OnClick(isFlagged, minesAround);
        if(isMined==null)
            return null;
        if (!isMined && minesAround == 0) {
            OnClickAround(pos);
        }
        Log.e("myTag", ","+this.cellsToWinCount);
         this.cellsToWinCount--;
        return isMined;
    }


    private void OnClickAround(int pos) {
        if (isCellValid(pos,pos + 1)) {
            OnClick(pos + 1, false);
        }
        if (isCellValid(pos,pos - 1)) {
            OnClick(pos - 1, false);
        }
        if (isCellValid(pos,pos + 1 + this.mSize)) {
            OnClick(pos + 1 + this.mSize, false);
        }
        if (isCellValid(pos,pos + this.mSize)) {
            OnClick(pos + this.mSize, false);
        }
        if (isCellValid(pos,pos - this.mSize)) {
            OnClick(pos - this.mSize, false);
        }
        if (isCellValid(pos,pos + 1 - this.mSize)) {
            OnClick(pos + 1 - this.mSize, false);
        }
        if (isCellValid(pos,pos - 1 - this.mSize)) {
            OnClick(pos - 1 - this.mSize, false);
        }
        if (isCellValid(pos,pos - 1 + this.mSize)) {
            OnClick(pos - 1 + this.mSize, false);
        }
    }

    public int getBoardSize() {
        return this.mSize*this.mSize;
    }

    public Cell getCell(int pos) {
        return mCells[pos];
    }

    private int CheckHowManyMinesAround(int pos) {
        int minesCount = 0;

        if (isCellValid(pos, pos + 1))
            if (mCells[pos + 1].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1))
            if (mCells[pos - 1].isMined())
                minesCount++;
        if (isCellValid(pos, pos + 1 + this.mSize))
            if (mCells[pos + 1 + this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos + this.mSize))
            if (mCells[pos + this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - this.mSize))
            if (mCells[pos - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos + 1 - this.mSize))
            if (mCells[pos + 1 - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1 - this.mSize))
            if (mCells[pos - 1 - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1 + this.mSize))
            if (mCells[pos - 1 + this.mSize].isMined())
                minesCount++;

        return minesCount;
    }

    public boolean isCellValid(int pos, int nextPos) {

        int row = pos / this.mSize;
        int col = pos % this.mSize;
        int newRow = nextPos / this.mSize;
        int newCol = nextPos % this.mSize;

        if (nextPos < 0 || nextPos >= this.mCells.length){
            return false;
        }
        if(newRow < 0 || newRow >= this.mSize){
            return false;
        }
        if (newCol < 0 || newCol >= this.mSize){
            return false;
        }
        if (col == this.mSize - 1 && newCol == 0){
            return false;
        }
        if (col == 0 && newCol == this.mSize - 1){
            return false;
        }
        return true;
    }


    public void sensorIsActive() {
        Random r = new Random();

        //Check if all cells Uncovered
        if(cellsToWinCount+numOfMines==mSize*mSize) {
            boolean mineAdded = false;
            while (!mineAdded) {
                int newMinePos = r.nextInt(this.mSize * this.mSize);
                if (!this.mCells[newMinePos].isMined()) {
                    this.mCells[newMinePos].setMined(true);
                    this.numOfMines++;
                    this.cellsToWinCount--;
                    mineAdded = true;
                }
            }
        }else {
            boolean cellCovered = false;
            while (!cellCovered) {
                int cellToUnCoverPos = r.nextInt(this.mSize * this.mSize);
                if (this.mCells[cellToUnCoverPos].getmState() != Cell.State.UNCOVERED) {
                    this.mCells[cellToUnCoverPos].setState(Cell.State.UNCOVERED);
                    this.cellsToWinCount++;
                    cellCovered = true;
                }
            }
        }
    }
    public int getCellsToWinCount() {
        return cellsToWinCount;
    }
    public int getNumOfMines(){ return  this.numOfMines; }
}
