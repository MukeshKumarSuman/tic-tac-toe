package com.nps.strategies.winningstrategy;

import com.nps.models.Board;
import com.nps.models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move currentMove);
    public void handleUndo(Board board, Move move);
}
