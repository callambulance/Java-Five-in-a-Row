package com.codecool.fiveinarow;
import java.util.Scanner;
import java.util.Arrays;

public class Game implements GameInterface {

    //  Creating new scanner object which is used to get user input
    private Scanner scanner = new Scanner(System.in);

    private int[][] board;

    Player player1;
    Player player2;

//    static final PLAYER1NUMBER = 1;
//    static final PLAYER1NUMBER = 2;

    public Game(int nRows, int nCols, Player player1,  Player player2) {
        this.board = new int[nRows][nCols];
        this.player1 = player1;
        this.player2 = player2;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player, String playerName) {

        while (true) {
            Move move = UserInteraction.getUserMove(player, playerName);

            if (move.getRow() > board.length - 1) {
                System.out.println("Invalid row index, try again.");
            } else if (move.getColumn() > board[0].length - 1) {
                System.out.println("Invalid column index, try again.");
            } else if (board[move.getRow()][move.getColumn()] != 0){
                System.out.println("Already taken, try again.");
            } else {
                return move.toArray();
            }
        }


    }

    public int[] getAiMove(int player, int howMany) {
//        example just for checking if it works
        int x = 0; // x - row
        int y = 0;  // y - column

        int[] playerCord = null;
        for (int i = howMany-1; i > 0; i--) {
            playerCord = AIwon(i, player);
            if (playerCord != null) {
                break;
            }
        }
        int [] move;
        if (playerCord == null) {
            while (board[x][y] != 0) {
                x = (int) (Math.random() * board.length);
                y = (int) (Math.random() * board[0].length);
            }
            move = new int[]{x, y};
        } else {
            move = playerCord;
        }

        return move;
    }


    public int[] AIwon (int howMany, int player) {
        int[] playerLose;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if ((j + howMany-1 < board[0].length) && board[i][j] == player) {
                    playerLose = checkIa(player, howMany-1, i, j, true);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if ((i +howMany-1 < board.length) && board[i][j] == player) {
                    playerLose = checkIa(player, howMany-1, i, j, false);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if (((i != 0 && i +howMany-1 < board.length) && (j != 0 && j + howMany-1 < board[0].length))
                        && board[i][j] == player) {
                    playerLose = checkIaDiagonal(player, howMany-1, i, j, true);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if (((i != 0 && i +howMany-1 < board.length) && (j != board[i].length-1 && j - howMany+1 >= 0)) && board[i][j] == player) {
                    playerLose = checkIaDiagonal(player, howMany-1, i, j, false);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
            }
        }
        return null;
    }

    public int[] AIdontLose (int howMany, int player) {
        int[] playerLose;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if ((j != 0 && j + howMany-2 < board[0].length) && board[i][j] == 1) {
                    playerLose = checkIa(1, howMany-2, i, j, true);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if ((i != 0 && i +howMany-2 < board.length) && board[i][j] == 1) {
                    playerLose = checkIa(1, howMany-2, i, j, false);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if (((i != 0 && i +howMany-2 < board.length) && (j != 0 && j + howMany-2 < board[0].length))
                        && board[i][j] == 1) {
                    playerLose = checkIaDiagonal(1, howMany-2, i, j, true);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
                if (((i != 0 && i +howMany-2 < board.length) && (j != 0 && j - howMany+2 >= 0)) && board[i][j] == 1) {
                    playerLose = checkIaDiagonal(1, howMany-2, i, j, false);
                    if (playerLose != null) {
                        return playerLose;
                    }
                }
            }
        }
        return null;
    }

    public void mark(int player, int row, int col) {
        board[row][col] = player;
    }

    public boolean hasWon(int player, int howMany) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == player) {
                    if (j + howMany <= board[i].length) {
                        if (checkHorizontalAndVertical(player, howMany, i, j, true)){
                            return true;
                        }
                    }
                    if (i + howMany <= board.length) {
                        if (checkHorizontalAndVertical(player, howMany, i, j, false)){
                            return true;
                        }
                    }
                    if (i + howMany <= board.length && j + howMany <= board[i].length){
                        if (checkDiagonal(player, howMany, i, j, true)){
                            return true;
                        }
                    }
                    if (i + howMany <= board.length && j - howMany >= -1){
                        if (checkDiagonal(player, howMany, i, j, false)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {

        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

//      1st part prints col digits
        System.out.println();
        System.out.print(" ");
        for (int colCounter = 1; colCounter <= board[0].length; colCounter++){
            if (colCounter >= 10){
                System.out.print(" " + colCounter);
            } else {
                System.out.print("  " + colCounter);
            }

        }
        System.out.println();

//      2nd part prints a letter
        for (int i = 0; i < board.length; i++){
            System.out.print(alphabet[i] + "  ");
//          and '.' if element is 0, 'X' if element is 1, 'O' if element is '2'
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 0) {
                    System.out.print('.' + "  ");
                } else if (board[i][j] == 1){
                    System.out.print('X' + "  ");
                } else {
                    System.out.print('O' + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printResult(int player) {
        if(player == 1) {
            System.out.println(player1.getName() + " won!");
        } else if(player == 2){
            System.out.println(player2.getName() + " won!");
        }
        else if (player == 0) {
            System.out.println("It's a tie!");
        }
    }

    public void enableAi(int player) {
//        making second player an AI
        player2.setPlayerType('A');
    }

    public static void quitGame(){
        System.out.println("Thank you for playing! Bye.");
        System.exit(0);
    }

    public void play(int howMany) {
        Player player = player1;
        int [] playerMove;
        boolean gameIsGoing = true;
        printBoard();

        while (gameIsGoing){
//            checking if player is Human or not
            if (player.getPlayerType() == 'H') {
                playerMove = getMove(player.getNumber(), player.getName());
            }
            else{
//                if player is AI
                playerMove = AIwon(howMany, player.getNumber());
                if (playerMove == null){
                    playerMove = AIdontLose(howMany, player.getNumber());
                    if(playerMove == null){
                        playerMove = getAiMove(player.getNumber(), howMany);
                    }
                }
            }
            mark(player.getNumber(), playerMove[0], playerMove[1]);
            printBoard();

            //            check if someone won
            if (hasWon(player.getNumber(), howMany)){
                //                if yes
                printResult(player.getNumber());
                printBoard();
                gameIsGoing = false;
            }
            //            if not check if board is full
            else{
                if (!isFull()){
                    //                    if yes
                    int playerNumber = 0;
                    printResult(playerNumber);
                    printBoard();
                    gameIsGoing = false;
                }else{
                    if(player.getNumber() == player1.getNumber()){
                        player = player2;
                    }else{
                        player = player1;
                    }
                }
            }
        }
    }

    private boolean checkHorizontalAndVertical(int player, int howMany, int i, int j, boolean horizontal) {
        if (horizontal) {
            for (int x = j; x < j + howMany; x++) {
                if (board[i][x] != player) {
                    return false;
                }
            }
        } else {
            for (int x = i; x < i + howMany; x++) {
                if (board[x][j] != player) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkDiagonal(int player, int howMany, int i, int j, boolean plus) {
        for (int x = 1; x < howMany; x++){
            if (plus) {
                if (board[i + x][j + x] != player) {
                    return false;
                }
            } else {
                if (board[i + x][j - x] != player) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] checkIa(int player, int howMany, int i, int j, boolean horizontal) {
        int[] move = new int[2];
        if (horizontal) {
            for (int x = j; x < j + howMany; x++) {
                if (board[i][x] != player) {
                    return null;
                }
            }
            if (j > 0) {
                if (board[i][j - 1] == 0 && board[i][j + howMany] == 0) {
                    move[0] = i;
                    move[1] = j - 1;
                } else if (board[i][j - 1] == 0 && board[i][j + howMany] == player) {
                    move[0] = i;
                    move[1] = j - 1;
                } else if (board[i][j - 1] == player && board[i][j + howMany] == 0) {
                    move[0] = i;
                    move[1] = j + howMany;
                } else {
                    move = null;
                }
            } else {
                if (board[i][j + howMany] == 0) {
                    move[0] = i;
                    move[1] = j + howMany;
                } else {
                    move = null;
                }
            }
        }else {
            for (int x = i; x < i + howMany; x++) {
                if (board[x][j] != player) {
                    return null;
                }
            }
            if (i > 0) {
                if (board[i - 1][j] == 0 && board[i + howMany][j] == 0) {
                    move[0] = i - 1;
                    move[1] = j;
                } else if (board[i - 1][j] == 0 && board[i + howMany][j] == player) {
                    move[0] = i - 1;
                    move[1] = j;
                } else if (board[i - 1][j] == player && board[i + howMany][j] == 0) {
                    move[0] = i + howMany;
                    move[1] = j;
                } else {
                    move = null;
                }
            } else {
                if (board[i + howMany][j] == 0) {
                    move[0] = i + howMany;
                    move[1] = j;
                } else {
                    move = null;
                }
            }
        }
        return move;
    }

    private int[] checkIaDiagonal(int player, int howMany, int i, int j, boolean plus) {
        int[] move = new int[2];
        for (int x = 1; x < howMany; x++){
            if (plus) {
                if (board[i + x][j + x] != player) {
                    return null;
                }
            } else {
                if (board[i + x][j - x] != player) {
                    return null;
                }
            }
        }
        if (plus) {
            if (board[i-1][j-1] == 0 && board[i+howMany][j+howMany] == 0) {
                move[0] = i+howMany;
                move[1] = j+howMany;
            }else if (board[i-1][j-1] == 0 && board[i+howMany][j+howMany] == player) {
                move[0] = i-1;
                move[1] = j-1;
            }else if (board[i-1][j-1] == player && board[i+howMany][j+howMany] == 0) {
                move[0] = i+howMany;
                move[1] = j+howMany;
            }else {
                move = null;
            }
        } else {
            if (board[i-1][j+1] == 0 && board[i+howMany][j-howMany] == 0) {
                move[0] = i+howMany;
                move[1] = j-howMany;
            }else if (board[i-1][j+1] == 0 && board[i+howMany][j-howMany] == player) {
                move[0] = i-1;
                move[1] = j+1;
            }else if (board[i-1][j-1] == player && board[i+howMany][j-howMany] == 0) {
                move[0] = i+howMany;
                move[1] = j-howMany;
            }else {
                move = null;
            }
        }
        return move;
    }
}
