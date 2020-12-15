package com.codecool.fiveinarow;

public class Player {

    private String name;
    private String color;
    private int number;
    private char playerType = 'H';

    public Player(String name, int number, char playerType) {
        this.name = name;
        this.number = number;
        this.playerType = playerType;
    }

    public Player(String name,String color, int number) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

    public String getName() { return name; }

    public String getColor() { return color; }

    public char getPlayerType(){ return playerType; }

    public void setPlayerType(char playerType) { this.playerType = playerType; }

    public void setName(String name) { this.name = name; }

    public void setColor(String color) { this.color = color; }

    public int getNumber() { return number; }

//    public void setNumber(int number) { this.number = number; }


}
