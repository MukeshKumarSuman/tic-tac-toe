package com.nps.strategies.winningstrategy;

import com.nps.models.Board;
import com.nps.models.Move;
import com.nps.models.Player;
import com.nps.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {

    private Map<Symbol, Integer> leftDiagonal; // Top left to bottom right
    private Map<Symbol, Integer> rightDiagonal; // Bottom left to top right

    public DiagonalWinningStrategy(int boardSize, List<Player> players) {
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        for (Player p: players) {
            leftDiagonal.put(p.getSymbol(), 0);
            rightDiagonal.put(p.getSymbol(), 0);
        }
    }
    @Override
    public boolean checkWinner(Board board, Move currentMove) {
        Symbol symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();
        if (row == col) { // we are on left diagonal
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
        }
        if (row + col == board.getSize() - 1) {// r we are on right diagonal
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
        }
        return leftDiagonal.get(symbol) == board.getSize() || rightDiagonal.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if (row == col) { // we are on left diagonal
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) - 1);
        }
        if (row + col == board.getSize() - 1) {// r we are on right diagonal
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) - 1);
        }
    }
}
