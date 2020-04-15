package com.example.minesweeper_game_app.logic;

import java.util.Random;

public class Board {
    private Cell[] mCells;
    private int mSize;

    public Board(int mSize) {
        setmSize(mSize);
        this.mCells = new Cell[this.mSize * this.mSize];
        CreateNewBoard();
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public void CreateNewBoard() {
        //create cells
        for (int i = 0; i < this.mSize * this.mSize; i++)
            this.mCells[i] = new Cell();
        //put mines at random positions
        Random r = new Random();
        int minePos;
        for (int i = 0; i < this.mSize * 2; i++) {
            minePos = r.nextInt(this.mSize * this.mSize);
            if (mCells[minePos].isMined())
                i--;
            else
                mCells[minePos].setMined(true);
        }
    }

    public Boolean OnClick(int pos, boolean isFlagged) {
        int minesAround = CheckHowManyMinesAround(pos);

        Boolean isMined = mCells[pos].OnClick(isFlagged, minesAround);
        if (!isMined && minesAround == 0) {
            OnClickAround(pos);
        }
        return isMined;
    }

    private void OnClickAround(int pos) {
        if (isCellValid(pos + 1)) {
            OnClick(pos + 1, false);
        }
        if (isCellValid(pos - 1)) {
            OnClick(pos - 1, false);
        }
        if (isCellValid(pos + 1 + this.mSize)) {
            OnClick(pos + 1 + this.mSize, false);
        }
        if (isCellValid(pos + this.mSize)) {
            OnClick(pos + this.mSize, false);
        }
        if (isCellValid(pos - this.mSize)) {
            OnClick(pos - this.mSize, false);
        }
        if (isCellValid(pos + 1 - this.mSize)) {
            OnClick(pos + 1 - this.mSize, false);
        }
        if (isCellValid(pos - 1 - this.mSize)) {
            OnClick(pos - 1 - this.mSize, false);
        }
        if (isCellValid(pos - 1 + this.mSize)) {
            OnClick(pos - 1 + this.mSize, false);
        }
    }

    public int getBoardSize() {
        return this.mCells.length;
    }

    public Cell getCell(int pos) {
        return mCells[pos];
    }

    private int CheckHowManyMinesAround(int pos) {
        int minesCount = 0;

        if (mCells[pos + 1].isMined() && isCellValid(pos + 1))
            minesCount++;
        if (mCells[pos - 1].isMined() && isCellValid(pos - 1))
            minesCount++;
        if (mCells[pos + 1 + this.mSize].isMined() && isCellValid(pos + 1 + this.mSize))
            minesCount++;
        if (mCells[pos + this.mSize].isMined() && isCellValid(pos + this.mSize))
            minesCount++;
        if (mCells[pos - this.mSize].isMined() && isCellValid(pos - this.mSize))
            minesCount++;
        if (mCells[pos + 1 - this.mSize].isMined() && isCellValid(pos + 1 - this.mSize))
            minesCount++;
        if (mCells[pos - 1 - this.mSize].isMined() && isCellValid(pos - 1 - this.mSize))
            minesCount++;
        if (mCells[pos - 1 + this.mSize].isMined() && isCellValid(pos - 1 + this.mSize))
            minesCount++;

        return minesCount;
    }

    public boolean isCellValid(int pos) {
        int row = pos / this.mSize;
        int col = pos % this.mSize;
        return (row >= 0) && (row < this.mSize) && (col >= 0) && (col < this.mSize);
    }
}
