package com.codecool.fiveinarow;

public class Player {

    private String name;
    private String color;
    private final int number;
    private char playerType;

    public Player(String name, int number, char playerType) {
        this.name = name;
        this.number = number;
        this.playerType = playerType;
    }

    public Player(String name,String color, int number, char playerType) {
        this.name = name;
        this.color = color;
        this.number = number;
        this.playerType = playerType;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public char getPlayerType(){ return playerType; }

    public void setPlayerType(char playerType) { this.playerType = playerType; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public int getNumber() { return number; }

//    public void setNumber(int number) { this.number = number; }


}
