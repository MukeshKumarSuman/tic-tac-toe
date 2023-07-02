package com.nps;

import com.nps.controller.GameController;
import com.nps.models.*;
import com.nps.strategies.winningstrategy.ColumnWinningStrategy;
import com.nps.strategies.winningstrategy.DiagonalWinningStrategy;
import com.nps.strategies.winningstrategy.RowWinningStrategy;
import com.nps.strategies.winningstrategy.WinningStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
         1. Game is started
         2. The current state of the Game
            | | | |
            | | | |
            | | | |

        3. Does anyone want to do undo(y/n) if y -> 2.
        4. It is X turn. Please enter row and col where want to make move(For Human). Player.makeMove
        5. If bot then BotPlayingStrategy return the respective cell.
        6. If Human Player then Player.makeMove take entry from user. If bot then Bot.makeMove call Strategy to get cell
        7. Check winner
        8. Change the Game status ig some winn or no more move left.
        9. If game status is still in progress again repeat from 2.

         */

        Scanner sc = new Scanner(System.in);

        Player p1 = new Player("Mukesh", new Symbol('0'), PlayerType.HUMAN);
        Player p2 = new Bot(BotDifficultyLevel.EASY, "Bot", new Symbol('X'));
        List<Player> players = Arrays.asList(p1, p2);
        int gameSize = 3;
        List<WinningStrategy> winningStrategies =
                Arrays.asList(new RowWinningStrategy(gameSize, players),
                        new ColumnWinningStrategy(gameSize, players), new DiagonalWinningStrategy(gameSize, players));


        GameController gc = new GameController();
        Game game = gc.startGame(gameSize, players, winningStrategies);

        System.out.println("-----Game is started-----");
        while(gc.getGameStatus(game) == GameStatus.IN_PROGRESS) {
            System.out.println("-------This is how board look like:------");
            gc.displayBoard(game);
            System.out.println("Do you want to undo?(y/n)");
            String userInput = sc.next();
            if ("y".equalsIgnoreCase(userInput)) {
                gc.undo(game);
            } else {
                gc.makeMove(game);
            }
        }
        gc.printResult(game);
    }
}
