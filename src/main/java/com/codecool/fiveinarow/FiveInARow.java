package com.codecool.fiveinarow;

public class FiveInARow {

    public static int rows = 3;
    public static int columns = 3;
    public static int howMany = 3;

    public static void main(String[] args) {


        Menu menu = new Menu();
        boolean isReady = false;
        while (!isReady){
            menu.printMainMenu();
            int mainMenuInput = menu.getMainMenuInput();
            if (mainMenuInput == 1){
                rows = menu.getBoardSize("rows");
                columns = menu.getBoardSize("columns");
            } else if (mainMenuInput == 4) {
                isReady = true;
            }
        }
        Game game = new Game(rows, columns);
        game.printBoard();
        int player1 = 1;
        int player2 = 2;
//
//        game.printBoard();
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
