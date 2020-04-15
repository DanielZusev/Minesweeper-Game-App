package com.example.minesweeper_game_app.logic;

public class Cell {

    public enum State {
        UNCOVERED, COVERED, FLAGGED, ONE, TWO, THREE, FOUR;
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
            ToggleFlage();
            return null;
        }
        else if (this.mState == State.UNCOVERED) {
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
                default:
                   // throw new IllegalStateException("Unexpected value: " + (howManyMinesAround));

            }
            return this.isMined;
        }
        return null;
    }

    public void ToggleFlage() {
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
