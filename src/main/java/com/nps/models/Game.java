package com.nps.models;

import com.nps.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;
    private int currentPlayerIndex;
    private Player winner;
    private GameStatus gameStatus;

    // Ensure all attributes are initialized in constructor
    private Game(int size, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.moves = new ArrayList<>();
        this.board = new Board(size);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.currentPlayerIndex = 0;
//        this.winner = null; // it is by default null
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Game setMoves(List<Move> moves) {
        this.moves = moves;
        return this;
    }

    public Board getBoard() {
        return board;
    }

    public Game setBoard(Board board) {
        this.board = board;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Game setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
        return this;
    }

    public Player getWinner() {
        return winner;
    }

    public Game setWinner(Player winner) {
        this.winner = winner;
        return this;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Game setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        return this;
    }

    public void displayBoard() {
        this.board.printBoard();
    }

    public void printWinner() {
        System.out.println("Game winner is " + winner.getName());
    }

    public void printResult() {
        if (getGameStatus() == GameStatus.ENDED) {
            System.out.println("Game ended!!");
            printWinner();
        } else {
            System.out.println("Game is draw!");
        }
    }

    public void makeMove() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Now " + currentPlayer.getName() + "'s turn!");
        Cell proposeCell = currentPlayer.makeMove(board);
        int row = proposeCell.getRow();
        int col = proposeCell.getCol();
        System.out.println("Move made at row:" + row + " and col:" + col);
        if (!validateMove(proposeCell)) {
            System.out.println("Please try again");
            return;
        }

        Cell cellInBoard = board.getBoard().get(row).get(col);
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);
        for (WinningStrategy winningStrategy: winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
                return;
            }
        }
        if (moves.size() == board.getSize() * board.getSize()) {
            gameStatus = GameStatus.DRAW;
            return;
        }

        currentPlayerIndex += 1;
        currentPlayerIndex %= players.size();
    }
    private boolean validateMove(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        if (row >= board.getSize() || col >= board.getSize()) {
            return false;
        }
        return board.getBoard().get(row).get(col).getCellStatus() == CellStatus.EMPTY;
    }

    public void undo() {
        if (moves.size() == 0) {
            System.out.println("No move to do undo!");
            return;
        }
        Move move = moves.remove(moves.size() - 1);
        Cell cell = move.getCell();
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);

        for (WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(board, move);
        }

        currentPlayerIndex -= 1;
        currentPlayerIndex += players.size();
        currentPlayerIndex %= players.size();
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder(){}

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() {
            if (!valid()) {
                throw new RuntimeException("Invalid game input");
            }
            return new Game(size, players, winningStrategies);
        }

        private boolean valid() {
            if (this.players.size() < 2) {
                return false;
            }
            if (this.players.size() != this.size - 1) {
                return false;
            }
            int botCount = 0;
            for (Player p: players) {
                if (p.getPlayerType() == PlayerType.BOT) {
                    botCount += 1;
                }
            }
            if (botCount > 1) {
                return false;
            }

            Set<Character> set = new HashSet<>();
            for (Player p: players) {
                if (set.contains(p.getSymbol().getSymbol())) {
                    return false;
                }
                set.add(p.getSymbol().getSymbol());
            }
            return true;
        }
    }
}

