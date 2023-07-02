package com.nps.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    private Scanner scanner;
    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Cell makeMove(Board board) {
        int size = board.getSize();
        System.out.println("Please enter the row number from 0 to " +  (size - 1));
        int row = this.scanner.nextInt();

        System.out.println("Please enter the col number from 0 to " +  (size - 1));
        int col = this.scanner.nextInt();
        return new Cell(row, col);
    }
}
