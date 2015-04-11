package com.avalon.backend.beans.gamesession;

import com.avalon.backend.beans.cards.Card;
import com.avalon.backend.beans.cards.WhiteCard;
import com.avalon.backend.beans.user.GameUser;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Player {

    private String id;
    private GameUser gameUser;
    private List<WhiteCard> hand;
    private int points;
    private boolean hasJoined;

    public Player(GameUser gameUser){
        this(gameUser, null);
    }

    public Player(GameUser gameUser, Entity entity){

        this.setGameUser(gameUser);
        if(entity != null){
            this.setId((String) entity.getProperty("Id"));
            this.setHand((List<WhiteCard>)entity.getProperty("Hand"));
            this.setPoints((Integer) entity.getProperty("Points"));
            this.setHasJoined((Boolean) entity.getProperty("HasJoined"));
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameUser getGameUser() {
        return gameUser;
    }

    public void setGameUser(GameUser gameUser) {
        this.gameUser = gameUser;
    }

    public List<WhiteCard> getHand() {
        return hand;
    }

    public void setHand(List<WhiteCard> hand) {
        this.hand = hand;
    }

    public WhiteCard getCard(int index){
        return this.getHand().get(index);
    }

    public boolean removeCard(WhiteCard card){
        return this.getHand().remove(card);
    }

    public void addCard(WhiteCard card){
        this.getHand().add(card)
;    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isHasJoined() {
        return hasJoined;
    }

    public void setHasJoined(boolean hasJoined) {
        this.hasJoined = hasJoined;
    }

    public Entity toEntity(){

        Entity entity = new Entity("Player");

        entity.setProperty("id", this.getId());
        entity.setProperty("GameUser", this.getGameUser().toEmbedded());
        entity.setProperty("Points", this.getPoints());
        entity.setProperty("Hand", this.getHand());

        return entity;
    }

    public void incrementPoints(){
        this.setPoints(this.getPoints()+1);
    }

}
