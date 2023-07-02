package com.nps.strategies.winningstrategy;

import com.nps.models.Board;
import com.nps.models.Move;
import com.nps.models.Player;
import com.nps.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> rowMaps;

    public RowWinningStrategy(int boardSize, List<Player> players) {
        rowMaps = new HashMap<>();
        for (int i = 0; i< boardSize; ++i) {
            Map<Symbol, Integer> countMap = new HashMap<>();
            for (Player p: players) {
                countMap.put(p.getSymbol(), 0);
            }
            rowMaps.put(i, countMap);
        }
    }
    @Override
    public boolean checkWinner(Board board, Move currentMove) {
        int row = currentMove.getCell().getRow();
        Symbol symbol = currentMove.getPlayer().getSymbol();
        Map<Symbol, Integer> symbolCountMap = this.rowMaps.get(row);
        symbolCountMap.put(symbol, symbolCountMap.get(symbol) + 1);
        return symbolCountMap.get(symbol) == board.getSize();
    }
//    {X: 0, y: 0} |
//    {X: 0, y: 0} |
//    {X: 0, y: 0} |

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> symbolCountMap = this.rowMaps.get(row);
        symbolCountMap.put(symbol, symbolCountMap.get(symbol) - 1);
    }
}
