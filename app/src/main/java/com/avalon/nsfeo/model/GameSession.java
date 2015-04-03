package com.avalon.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.field.IntegerField;
import com.orm.androrm.field.ManyToManyField;

import java.util.List;

/**
 * Created by Tonaz on 3/3/2015.
 */
public class GameSession extends Model {

    protected ManyToManyField<GameSession, User> players = new ManyToManyField<>(GameSession.class, User.class);
    protected ForeignKeyField<Deck> toBePlayed = new ForeignKeyField<>(Deck.class);
    protected ForeignKeyField<Deck> played = new ForeignKeyField<>(Deck.class);
    protected ManyToManyField<GameSession, Card> hand = new ManyToManyField<>(GameSession.class, Card.class);
    protected IntegerField selectedCard = new IntegerField();
    protected ManyToManyField<GameSession, Card> playedCards = new ManyToManyField<>(GameSession.class, Card.class);

    public static QuerySet<GameSession> objects(Context ctx) { return Model.objects(ctx, GameSession.class); }

    public GameSession(){
        super();
    }

    public QuerySet<User> getPlayers(Context ctx) {
        return players.get(ctx, this);
    }

    public void setPlayers(List<User> players) {
        this.players.addAll(players);
    }

    public Deck getToBePlayed(Context ctx) {
        return toBePlayed.get(ctx);
    }

    public void setToBePlayed(Deck toBePlayed) {
        this.toBePlayed.set(toBePlayed);
    }

    public Deck getPlayed(Context ctx) {
        return played.get(ctx);
    }

    public void setPlayed(Deck played) {
        this.played.set(played);
    }

    public QuerySet<Card> getHand(Context ctx) {
        return hand.get(ctx, this);
    }

    public void setHand(List<Card> hand) {
        this.hand.addAll(hand);
    }

    public int getSelectedCard() {
        return selectedCard.get();
    }

    public void setSelectedCard(int selectedCard) {
        this.selectedCard.set(selectedCard);
    }

    public QuerySet<Card> getPlayedCards(Context ctx) {
        return playedCards.get(ctx, this);
    }

    public void setPlayedCards(List<Card> playedCards) {
        this.playedCards.addAll(playedCards);
    }
}
