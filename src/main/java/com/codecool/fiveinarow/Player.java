package com.codecool.fiveinarow;

public class Player {

    private String name;
    private String color;
    private int number;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Player(String name,String color, int number) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

    public String getName() { return name; }

    public String getColor() { return color; }

    public void setName(String name) { this.name = name; }

    public void setColor(String color) { this.color = color; }

    public int getNumber() { return number; }

//    public void setNumber(int number) { this.number = number; }


}
