package com.nps.controller;

import com.nps.models.Game;
import com.nps.models.GameStatus;
import com.nps.models.Player;
import com.nps.strategies.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Player> players,
                          List<WinningStrategy> winningStrategies) {
        return Game.getBuilder().setSize(size)
                .setPlayers(players).setWinningStrategies(winningStrategies)
                .build();
    }
    public void displayBoard(Game game) {
        game.displayBoard();
    }
    public void undo(Game game) {
        game.undo();
    }
    public void makeMove(Game game) {
        game.makeMove();
    }
    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }
    public void printWinner(Game game) {
        game.printWinner();
    }
    public void printResult(Game game) {
        game.printResult();
    }
}


