package com.codecool.fiveinarow;
import java.util.Scanner;
import java.util.Arrays;

public class Game implements GameInterface {

    //  Creating new scanner object which is used to get user input
    private Scanner scanner = new Scanner(System.in);

    private int[][] board;

    public Game(int nRows, int nCols) {
        this.board = new int[nRows][nCols];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {

        int row = 0;
        int col = 0;

        boolean isInputValid = false;

        while (isInputValid == false){
            System.out.print("Player" + player + " turn: ");

//          saving user input as a String
            String userInput = scanner.nextLine();

            if (userInput.equals("quit")){
                quitGame();
            } else if (userInput.length() != 2) {
                System.out.println("Invalid number of characters, try again.");
                continue;
            } else if (!Character.isLetter(userInput.charAt(0))){
                System.out.println("Invalid row character, try again.");
                continue;
            } else if (!Character.isDigit(userInput.charAt(1))){
                System.out.println("Invalid column character, try again.");
                continue;
            }

//          converting userInput[0] to numeric value
            char upperLetter = Character.toUpperCase(userInput.charAt(0));
            row = ((int)upperLetter - (int)'A');

//          converting userInput[1] to int and substracting 1
//          in order to get correct col index
            col = Character.getNumericValue(userInput.charAt(1)) - 1;

            if (row > board.length - 1) {
                System.out.println("Invalid row index, try again.");
                continue;
            } else if (col > board[0].length - 1) {
                System.out.println("Invalid column index, try again.");
                continue;
            } else if (board[row][col] != 0){
                System.out.println("Already taken, try again.");
                continue;
            } else {
                isInputValid = true;
            }
        }

        int[] result = new int[] {row, col};

        return result;
    }

    public int[] getAiMove(int player) {
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
            System.out.print("  " + colCounter);
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
        if(player == 1){
            System.out.println("X won!");
        }else if(player == 2){
            System.out.println("O won!");
        }else{
            System.out.println("It's a tie!");
        }
    }

    public void enableAi(int player) {
    }

    public void quitGame(){
        System.out.println("Thank you for playing! Bye.");
        System.exit(0);
    }

    public void play(int howMany) {
        int player1 = 1;
        int player2 = 2;
        int player = player1;
        boolean gameIsGoing = true;
        printBoard();

        while (gameIsGoing){
            int [] playerMove = getMove(player);
            mark(player, playerMove[0], playerMove[1]);
            printBoard();
            System.out.println(player);
            //            check if someone won
            if (hasWon(player, howMany)){
                //                if yes
                printResult(player);
                printBoard();
                gameIsGoing = false;
            }
            //            if not check if board is full
            else{
                if (isFull() == false){
                    //                    if yes
                    player = 0;
                    printResult(player);
                    printBoard();
                    gameIsGoing = false;
                }else{
                    if(player == player1){
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
}

