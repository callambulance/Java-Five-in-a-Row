package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Settings settings = menu.getSettings();
        Player player1 = menu.player1;
        Player player2 = menu.player2;

        Game game = new Game(settings.getRows(), settings.getColumns(), player1.getName(), player2.getName());

        game.play(settings.getHowMany());

    }
}


