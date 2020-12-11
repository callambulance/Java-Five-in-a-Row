package com.codecool.fiveinarow;


import java.util.Scanner;

public class UserInteraction {

    private static final Scanner scanner = new Scanner(System.in);

    public static Move getUserMove(int player, String playerName){


        while (true){
            System.out.print(playerName + " turn: ");

//          saving user input as a String
            String userInput = scanner.nextLine();

            if (userInput.equals("quit")) {
                Game.quitGame();
            } else if (userInput.length() != 2 && userInput.length() != 3) {
                System.out.println("Invalid number of characters, try again.");
                continue;
            } else {
                if (!Character.isLetter(userInput.charAt(0))){
                    System.out.println("Invalid row character, try again.");
                    continue;
                } else if (!Character.isDigit(userInput.charAt(1)) || userInput.charAt(1) == '0'){
                    System.out.println("Invalid column character, try again.");
                    continue;
                } else if (userInput.length() == 3){
                    if (!Character.isDigit(userInput.charAt(2))){
                        System.out.println("Invalid column character, try again.");
                        continue;
                    }
                }
            }

            int row = getRow(userInput);
            int col = getColumn(userInput);
            return new Move(row, col);
        }
    }

    private static int getRow(String userInput){
        char upperLetter = Character.toUpperCase(userInput.charAt(0));
        return ((int)upperLetter - (int)'A');
    }

    private static int getColumn(String userInput){
        if (userInput.length() == 2){
            return Character.getNumericValue(userInput.charAt(1)) - 1;
        } else {
            String temp = "" + userInput.charAt(1) + userInput.charAt(2);
            return Integer.parseInt(temp) - 1;
        }
    }

}
