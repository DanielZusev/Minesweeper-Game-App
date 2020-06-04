package com.example.minesweeper_game_app.logic;

public class Player  implements Comparable{

    private String name;
    private int time;

    public Player() {
    }

    public Player(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Player player = (Player)o;
        return Integer.compare(this.getTime(), player.getTime());
    }
}
