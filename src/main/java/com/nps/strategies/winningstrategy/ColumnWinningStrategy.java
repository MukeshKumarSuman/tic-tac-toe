package com.nps.strategies.winningstrategy;

import com.nps.models.Board;
import com.nps.models.Move;
import com.nps.models.Player;
import com.nps.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> colMap;

    public ColumnWinningStrategy(int boardSize, List<Player> players) {
        colMap = new HashMap<>();
        for (int i = 0; i< boardSize; ++i) {
            Map<Symbol, Integer> countMap = new HashMap<>();
            for (Player p: players) {
                countMap.put(p.getSymbol(), 0);
            }
            colMap.put(i, countMap);
        }
    }
    @Override
    public boolean checkWinner(Board board, Move currentMove) {
        int col = currentMove.getCell().getCol();
        Symbol symbol = currentMove.getPlayer().getSymbol();
        Map<Symbol, Integer> symbolCountMap = this.colMap.get(col);
        symbolCountMap.put(symbol, symbolCountMap.get(symbol) + 1);
        return symbolCountMap.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> symbolCountMap = this.colMap.get(col);
        symbolCountMap.put(symbol, symbolCountMap.get(symbol) - 1);
    }
}
