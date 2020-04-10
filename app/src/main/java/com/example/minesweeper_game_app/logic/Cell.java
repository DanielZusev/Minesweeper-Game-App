package com.example.minesweeper_game_app.logic;

public class Cell {

    public enum State {
        UNCOVERED,COVERED,FLAGED,ONE,TWO,THREE,FOUR;


        @Override
        public String toString() {
            switch(this) {
                case UNCOVERED:
                    return "U";
                case COVERED:
                    return "C";
                case FLAGED:
                    return "F";
                default:
                    return "";
            }
        }
    }


    State mState;
    boolean isMined;
    public Cell(boolean isMined){
        this.isMined=isMined;
        mState=State.UNCOVERED;
    }
    public State getmState() {
        return mState;
    }


}
