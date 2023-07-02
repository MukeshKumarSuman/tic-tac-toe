package com.nps.strategies.botplayingstrategy;

import com.nps.models.Board;
import com.nps.models.Cell;
import com.nps.models.CellStatus;

import java.util.List;

public class EasyBoatPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell makeMove(Board board) {
        for (List<Cell> row: board.getBoard()) {
            for (Cell cell: row) {
                if (cell.getCellStatus() == CellStatus.EMPTY) {
                    return cell;
                }
            }
        }
        return null; // never we will reach at this point as this method
        // only called when there is left empty cell.
    }
}
