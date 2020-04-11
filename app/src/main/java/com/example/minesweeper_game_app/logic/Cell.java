package com.example.minesweeper_game_app.logic;

public class Cell {

    public enum State {
        UNCOVERED,COVERED,FLAGGED,ONE,TWO,THREE,FOUR;
        @Override
        public String toString() {
            switch(this) {
                case UNCOVERED:
                    return "U";
                case COVERED:
                    return "C";
                case FLAGGED:
                    return "F";
                default:
                    return "";
            }
        }
    }

    private State mState;
    private boolean isMined;

    public Cell(){
        this.isMined=false;
        mState=State.UNCOVERED;
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

    public Boolean OnClick(boolean isFlagged) {
        if (isFlagged) {
            ToggleFlage();
            return null;
        }
        if(this.mState==State.UNCOVERED){
            setStateToCovered();
            return this.isMined;
        }
        return null;
    }

    public void ToggleFlage(){
        if (this.mState==State.UNCOVERED)
            this.mState=State.FLAGGED;
        else if(this.mState==State.FLAGGED)
            this.mState=State.UNCOVERED;
    }
    public void setStateToCovered(){
        this.mState=State.COVERED;
    }
    public void setState(State state){
        this.mState=state;
    }

}
