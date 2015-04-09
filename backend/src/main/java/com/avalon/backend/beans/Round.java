package com.avalon.backend.beans;

import java.util.List;
import java.util.Map;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Round {

    private Card blackCard;
    private Map<Player, List<Card>> submissions;
    private int roundNumber;
    private int judgeIndex;
    private boolean isReady;

    public Card getBlackCard() {
        return blackCard;
    }

    public void setBlackCard(Card blackCard) {
        this.blackCard = blackCard;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getJudgeIndex() {
        return judgeIndex;
    }

    public void setJudgeIndex(int judgeIndex) {
        this.judgeIndex = judgeIndex;
    }
}
