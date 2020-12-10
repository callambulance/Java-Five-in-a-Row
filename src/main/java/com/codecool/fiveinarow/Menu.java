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

    private Settings settings = new Settings(3,3,3, "PLAYER VS. PLAYER");


    private Scanner scanner = new Scanner(System.in);


    public void printMenu() {

        System.out.println(logo);
        System.out.println("1.BOARD SIZE: " + settings.getRows() + 'x' + settings.getColumns());
        System.out.println("2.CHARACTERS TO WIN: " + settings.getHowMany());
        System.out.println("3.GAME MODE: " + settings.getGameMode());
        System.out.println("4. START GAME");
        System.out.println("0. QUIT GAME");
        System.out.print("You can change game settings by pressing corresponding key (0-4): ");

    }

    public Settings getSettings() {

        boolean settingsReady = false;
        String userInput = "";

        printMenu();
        while (!settingsReady) {
            userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Invalid number of characters, try again.");
            } else if (!Character.isDigit(userInput.charAt(0))) {
                System.out.println("Invalid character, try again.");
            } else if (Integer.parseInt(userInput) == 0) {
                Game.quitGame();
            } else if (Integer.parseInt(userInput) > 4) {
                System.out.println("Invalid digit, try again.");
            } else {

                if (userInput.charAt(0) == '1') {
                    settings.setRows(getBoardSize("rows"));
                    settings.setColumns(getBoardSize("columns"));
                    printMenu();
                } else if (userInput.charAt(0) == '2'){
                  settings.setHowMany(getHowMany());
                    printMenu();
                } else if (userInput.charAt(0) == '3'){
                    settings.setGameMode(getGameMode());
                    printMenu();
                } else if (userInput.charAt(0) == '4') {
                    settingsReady = true;
                }
            }
        }
        return settings;
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
            } else {
                for (int i = 0; i < userInput.length(); i++){
                    if (!Character.isDigit(userInput.charAt(i)) ) {
                        System.out.println("Invalid input, try again.");
                        continue outerloop;
                    }
                }

                if (Integer.parseInt(userInput) < 3 || Integer.parseInt(userInput) > 24){
                    System.out.println("Number out of range, try again.");
                } else {
                    isInputValid = true;
                }
            }
        }

        result = Integer.parseInt(userInput);
        return result;
    }


    public int getHowMany() {

        int result;
        boolean isInputValid = false;
        String userInput = "";

        System.out.println(logo);
        System.out.println("CHARACTERS TO WIN MENU: ");
        System.out.println("Provide number of characters needed to win the game (3-9): ");


        while (!isInputValid) {
            userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Invalid number of characters, try again.");
            } else if (!Character.isDigit(userInput.charAt(0))) {
                System.out.println("Invalid character, try again.");
            } else if (Integer.parseInt(userInput) == 0 || Integer.parseInt(userInput) < 3 || Integer.parseInt(userInput) > 9) {
                System.out.println("Invalid digit, try again.");
            } else {
                isInputValid = true;
            }
        }
        result = Integer.parseInt(userInput);
        return result;
    }


    public String getGameMode(){

        String result;
        boolean isInputValid = false;
        String userInput = "";

        System.out.println(logo);
        System.out.println("GAME MODE MENU: ");
        System.out.println("1. PLAYER VS. PLAYER");
        System.out.println("2. PLAYER VS. AI");

        while (!isInputValid) {
            userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Invalid number of characters, try again.");
            } else if (!Character.isDigit(userInput.charAt(0))) {
                System.out.println("Invalid character, try again.");
            } else if (Integer.parseInt(userInput) == 0 || Integer.parseInt(userInput) > 2) {
                System.out.println("Invalid digit, try again.");
            } else {
                isInputValid = true;
            }
        }
        if (userInput.charAt(0) == '1'){
            result = "PLAYER VS. PLAYER";
        } else {
            result = "PLAYER VS. AI";
        }
        return result;
    }
}