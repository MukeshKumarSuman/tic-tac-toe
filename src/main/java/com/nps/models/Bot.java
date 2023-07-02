package com.nps.models;

import com.nps.strategies.botplayingstrategy.BotPlayingStrategy;
import com.nps.strategies.factory.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel boatDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(BotDifficultyLevel boatDifficultyLevel, String name, Symbol symbol) {
        super(name, symbol, PlayerType.BOT);
        this.boatDifficultyLevel = boatDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(boatDifficultyLevel);
    }

    public BotDifficultyLevel getBoatDifficultyLevel() {
        return boatDifficultyLevel;
    }

    public void setBoatDifficultyLevel(BotDifficultyLevel boatDifficultyLevel) {
        this.boatDifficultyLevel = boatDifficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        return this.botPlayingStrategy.makeMove(board);
    }
}
