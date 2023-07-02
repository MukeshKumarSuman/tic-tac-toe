package com.nps.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private  List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            board.add(new ArrayList<>());
            for (int j = 0; j < size; ++j) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void printBoard() {
        for (List<Cell> row: board) {
            System.out.print("|");
            for(Cell col: row) {
                if(col.getCellStatus() == CellStatus.EMPTY) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + col.getPlayer().getSymbol().getSymbol() + " ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        // | - | X | 0 |
        // | - | - | - |
        // | 0 | - | - |
    }
}
