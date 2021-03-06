package com.example.minesweeper_game_app.logic;

public class Cell {

    public enum State {
        UNCOVERED, COVERED, FLAGGED, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE, BOOM
    }

    private State mState;
    private boolean isMined;

    public Cell() {
        this.isMined = false;
        mState = State.UNCOVERED;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public State getmState() {
        return mState;
    }

    public Boolean OnClick(boolean isFlagged, int howManyMinesAround) {
        if (isFlagged) {
            ToggleFlag();
            return null;
        }
        if (this.mState == State.FLAGGED)
            return null;
        if (this.isMined()) {
            setState(State.BOOM);
        } else if (this.mState == State.UNCOVERED) {
            switch ((howManyMinesAround)) {
                case 0:
                    setStateToCovered();
                    break;
                case 1:
                    setState(State.ONE);
                    break;
                case 2:
                    setState(State.TWO);
                    break;
                case 3:
                    setState(State.THREE);
                    break;
                case 4:
                    setState(State.FOUR);
                    break;
                case 5:
                    setState(State.FIVE);
                    break;
                case 6:
                    setState(State.SIX);
                    break;
                case 7:
                    setState(State.SEVEN);
                    break;
                case 8:
                    setState(State.EIGHT);
                    break;
            }
        }
        return this.isMined;
    }

    public void ToggleFlag() {
        if (this.mState == State.UNCOVERED)
            this.mState = State.FLAGGED;
        else if (this.mState == State.FLAGGED)
            this.mState = State.UNCOVERED;
    }

    public void setStateToCovered() {
        this.mState = State.COVERED;
    }

    public void setState(State state) {
        this.mState = state;
    }

}
