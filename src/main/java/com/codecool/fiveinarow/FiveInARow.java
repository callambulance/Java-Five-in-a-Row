package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Settings settings = menu.getSettings();

        Game game = new Game(settings.getRows(), settings.getColumns());
        game.play(settings.getHowMany());
    }
}
