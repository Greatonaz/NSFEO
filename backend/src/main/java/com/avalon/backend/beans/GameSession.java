package com.avalon.backend.beans;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class GameSession {

    // Game Session Management
    private String id;
    private String name;
    private boolean isSessionReady;

    // Player Management
    private List<Player> players;

    // Decks
    private List<Card> whiteDeck;
    private List<Card> blackDeck;
    private List<Card> grayDeck;

    // Discard piles
    private List<Card> usedWhiteDeck;
    private List<Card> usedBlackDeck;
    private List<Card> usedGrayDeck;

    // Round Management
    private List<Round> rounds;
    private int roundLimit;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public List<Card> getWhiteDeck() {
        return whiteDeck;
    }

    public void setWhiteDeck(List<Card> whiteDeck) {
        this.whiteDeck = whiteDeck;
    }

    public List<Card> getBlackDeck() {
        return blackDeck;
    }

    public void setBlackDeck(List<Card> blackDeck) {
        this.blackDeck = blackDeck;
    }

    public List<Card> getUsedWhiteDeck() {
        return usedWhiteDeck;
    }

    public void setUsedWhiteDeck(List<Card> usedWhiteDeck) {
        this.usedWhiteDeck = usedWhiteDeck;
    }

    public List<Card> getUsedBlackDeck() {
        return usedBlackDeck;
    }

    public void setUsedBlackDeck(List<Card> usedBlackDeck) {
        this.usedBlackDeck = usedBlackDeck;
    }

    public List<Card> getGrayDeck() {
        return grayDeck;
    }

    public void setGrayDeck(List<Card> grayDeck) {
        this.grayDeck = grayDeck;
    }

    public List<Card> getUsedGrayDeck() {
        return usedGrayDeck;
    }

    public void setUsedGrayDeck(List<Card> usedGrayDeck) {
        this.usedGrayDeck = usedGrayDeck;
    }

    public int getRoundLimit() {
        return roundLimit;
    }

    public void setRoundLimit(int roundLimit) {
        this.roundLimit = roundLimit;
    }

    public boolean isSessionReady() {
        return isSessionReady;
    }

    public void setSessionReady(boolean isSessionReady) {
        this.isSessionReady = isSessionReady;
    }
}
