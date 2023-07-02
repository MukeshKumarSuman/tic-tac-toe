package com.nps.strategies.botplayingstrategy;

import com.nps.models.Board;
import com.nps.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
