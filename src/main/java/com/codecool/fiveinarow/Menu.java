package com.codecool.fiveinarow;

import java.util.Scanner;

public class Menu {

    String logo = "                                                                                        \n" +
            "███████╗██╗██╗   ██╗███████╗    ██╗███╗   ██╗     █████╗     ██████╗  ██████╗ ██╗    ██╗\n" +
            "██╔════╝██║██║   ██║██╔════╝    ██║████╗  ██║    ██╔══██╗    ██╔══██╗██╔═══██╗██║    ██║\n" +
            "█████╗  ██║██║   ██║█████╗      ██║██╔██╗ ██║    ███████║    ██████╔╝██║   ██║██║ █╗ ██║\n" +
            "██╔══╝  ██║╚██╗ ██╔╝██╔══╝      ██║██║╚██╗██║    ██╔══██║    ██╔══██╗██║   ██║██║███╗██║\n" +
            "██║     ██║ ╚████╔╝ ███████╗    ██║██║ ╚████║    ██║  ██║    ██║  ██║╚██████╔╝╚███╔███╔╝\n" +
            "╚═╝     ╚═╝  ╚═══╝  ╚══════╝    ╚═╝╚═╝  ╚═══╝    ╚═╝  ╚═╝    ╚═╝  ╚═╝ ╚═════╝  ╚══╝╚══╝ \n" +
            "                                                                                        ";

    private String pvp = "Player vs Player";
    private String pvai = "Player vs AI";
    private Scanner scanner = new Scanner(System.in);

    public void printMainMenu() {


        System.out.println(logo);
        System.out.println("1.BOARD SIZE: " + FiveInARow.rows + 'x' + FiveInARow.columns);
        System.out.println("2.GAME MODE: " + pvp);
        System.out.println("3.CHARACTERS IN A ROW: " + FiveInARow.howMany);
        System.out.println("4. START GAME");
        System.out.print("You can change game settings by pressing corresponding key (1-4): ");

    }

    public int getMainMenuInput() {

        boolean isInputValid = false;
        String userInput = "";

        while (!isInputValid) {
            userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Invalid number of characters, try again.");
                continue;
            } else if (!Character.isDigit(userInput.charAt(0))){
                System.out.println("Invalid character, try again.");
                continue;
            } else if (Integer.parseInt(userInput) == 0 || Integer.parseInt(userInput) > 4 ) {
                System.out.println("Invalid digit, try again.");
                continue;
            } else {
                isInputValid = true;
            }
        }
        return Integer.parseInt(userInput);
    }


    public int getBoardSize(String rowsOrColumns) {

        int result;
        boolean isInputValid = false;
        String userInput = "";

        if (rowsOrColumns == "rows"){
            System.out.println(logo);
            System.out.println("BOARD SIZE MENU: ");
        }

        outerloop:
        while (!isInputValid) {
            System.out.print(String.format("Provide number of %s (3-24): ", rowsOrColumns));
            userInput = scanner.nextLine();

            if (userInput.length() == 0) {
                System.out.println("Invalid number of characters, try again.");
                continue;
            } else {
                for (int i = 0; i < userInput.length(); i++){
                    if (!Character.isDigit(userInput.charAt(i)) ) {
                        System.out.println("Invalid input, try again.");
                        continue outerloop;
                    }
                }

                if (Integer.parseInt(userInput) < 3 || Integer.parseInt(userInput) > 24){
                    System.out.println("Number out of range, try again.");
                    continue;
                } else {
                    isInputValid = true;
                }
            }
        }

        result = Integer.parseInt(userInput);
        return result;
    }
}