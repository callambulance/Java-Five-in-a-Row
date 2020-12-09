package com.codecool.fiveinarow;

public class Settings {
    private int rows;
    private int columns;
    private int howMany;
    private String gameMode;

    public Settings(int rows, int columns, int howMany, String gameMode) {
        this.rows = rows;
        this.columns = columns;
        this.howMany = howMany;
        this.gameMode = gameMode;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
