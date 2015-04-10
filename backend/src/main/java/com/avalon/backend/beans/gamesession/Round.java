package com.avalon.backend.beans.gamesession;

import com.avalon.backend.beans.cards.BlackCard;
import com.avalon.backend.beans.cards.Card;
import com.avalon.backend.beans.cards.GrayCard;
import com.avalon.backend.beans.cards.WhiteCard;
import com.google.appengine.api.datastore.Entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Round {

    private String id;
    private BlackCard challenge;
    private GrayCard modifier;
    private int judge;
    private int winner;
    private Map<Player, List<WhiteCard>> submissions;
    private boolean isReady;

    public Round(){

    }

    public Round(Entity entity){
        this.setId((String) entity.getProperty("Id"));
        this.setChallenge((BlackCard) entity.getProperty("Challenge"));
        this.setModifier((GrayCard) entity.getProperty("Modifier"));
        this.setJudge((Integer) entity.getProperty("Judge"));
        this.setWinner((Integer) entity.getProperty("Winner"));
        this.setSubmissions((Map<Player, List<WhiteCard>>) entity.getProperty("Submissions"));
        this.setReady((Boolean) entity.getProperty("Ready"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlackCard getChallenge() {
        return challenge;
    }

    public void setChallenge(BlackCard challenge) {
        this.challenge = challenge;
    }

    public GrayCard getModifier() {
        return modifier;
    }

    public void setModifier(GrayCard modifier) {
        this.modifier = modifier;
    }

    public int getJudge() {
        return judge;
    }

    public void setJudge(int judge) {
        this.judge = judge;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public Map<Player, List<WhiteCard>> getSubmissions() {
        return submissions;
    }

    public void addSubmission(Player player, List<WhiteCard> cards){
        this.getSubmissions().put(player, cards);
    }

    public void setSubmissions(Map<Player, List<WhiteCard>> submissions) {
        this.submissions = submissions;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    public Entity toEntity(){
        Entity entity = new Entity("Round");
        entity.setProperty("Id",this.getId());
        entity.setProperty("Challenge",this.getChallenge());
        entity.setProperty("Modifier",this.getModifier());
        entity.setProperty("Judge",this.getJudge());
        entity.setProperty("Winner", this.getWinner());
        entity.setProperty("Submissions", this.getSubmissions());
        entity.setProperty("Ready", this.isReady());
        return entity;
    }
}
