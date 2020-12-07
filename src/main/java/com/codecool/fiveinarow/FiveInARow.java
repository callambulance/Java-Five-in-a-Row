package com.codecool.fiveinarow;

public class FiveInARow {

    public static void main(String[] args) {
        Game game = new Game(5, 5);
        int player1 = 1;
        int player2 = 2;
        game.printBoard();
        while (true) {
            int [] player1Move = game.getMove(player1);
            game.mark(player1, player1Move[0], player1Move[1]);
            game.printBoard();
            int [] player2Move = game.getMove(player2);
            game.mark(player2, player2Move[0], player2Move[1]);
            game.printBoard();
        }
//        game.enableAi(1);
//        game.enableAi(2);
//        game.play(5);
    }
}
