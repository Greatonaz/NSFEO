package com.avalon.backend.beans;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Player {

    private GameUser gameUser;
    private List<Card> hand;
    private List<Round> round;
    private boolean hasJoined;
    private boolean hasRejected;

    public GameUser getGameUser() {
        return gameUser;
    }

    public void setGameUser(GameUser gameUser) {
        this.gameUser = gameUser;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    public List<Round> getRound() {
        return round;
    }

    public void setRound(List<Round> round) {
        this.round = round;
    }

    public boolean hasJoined() {
        return hasJoined;
    }

    public void setJoined(boolean hasJoined) {
        this.hasJoined = hasJoined;
    }

    public boolean hasRejected() {
        return hasRejected;
    }

    public void setRejected(boolean hasRejected) {
        this.hasRejected = hasRejected;
    }
}
