package com.avalon.backend.beans.gamesession;

import com.avalon.backend.beans.cards.Card;
import com.avalon.backend.beans.cards.WhiteCard;
import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class GameSession {

    // Game Session Management
    private String id;
    private String name;
    private String language;

    // Player Management
    private List<Player> players;

    // Decks
    private List<WhiteCard> deck;

    // Round Management
    private List<Round> rounds;
    private int roundLimit;
    private double max_response_time;

    public GameSession(Entity entity){
        this.setId((String) entity.getProperty("Id"));
        this.setName((String)entity.getProperty("Name"));
        this.setLanguage((String) entity.getProperty("Language"));
        this.setPlayers((List<Player>) entity.getProperty("Players"));
        this.setDeck((List<WhiteCard>) entity.getProperty("Cards"));
        this.setRounds((List<Round>) entity.getProperty("Rounds"));
        this.setRoundLimit((Integer) entity.getProperty("RoundLimit"));
        this.setMaxResponseTime((Integer) entity.getProperty("MaxResponseTime"));
    }

    public GameSession(){
        // Empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public void removePlayer(int index){
        this.players.remove(index);
    }

    public List<WhiteCard> getDeck() {
        return deck;
    }

    public void setDeck(List<WhiteCard> deck) {
        this.deck = deck;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getRound(int index){
        return this.rounds.get(index);
    }

    public int getRoundLimit() {
        return roundLimit;
    }

    public void setRoundLimit(int roundLimit) {
        this.roundLimit = roundLimit;
    }

    public double getMaxResponseTime() {
        return max_response_time;
    }

    public void setMaxResponseTime(double time) {
        this.max_response_time = time;
    }
}
