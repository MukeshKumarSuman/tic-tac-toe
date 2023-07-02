package com.nps.strategies.factory;

import com.nps.models.BotDifficultyLevel;
import com.nps.strategies.botplayingstrategy.BotPlayingStrategy;
import com.nps.strategies.botplayingstrategy.EasyBoatPlayingStrategy;
import com.nps.strategies.botplayingstrategy.HardBoatPlayingStrategy;
import com.nps.strategies.botplayingstrategy.MediumBoatPlayingStrategy;

public class BotPlayingStrategyFactory {
    public  static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        switch(botDifficultyLevel) {
            case MEDIUM: return new MediumBoatPlayingStrategy();
            case HARD: return new HardBoatPlayingStrategy();
            case EASY:
            default: return new EasyBoatPlayingStrategy();
        }
    }
}
