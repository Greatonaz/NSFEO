package com.avalon.studios.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.ManyToManyField;

import java.util.List;

/**
 * Created by Tonaz on 3/3/2015.
 */
public class Deck extends Model {

    protected ManyToManyField<Deck, Card> deck = new ManyToManyField<>(Deck.class, Card.class);

    public static QuerySet<Deck> objects(Context ctx) { return Model.objects(ctx, Deck.class); }

    public Deck(){
        super();
    }

    public QuerySet<Card> getDeck(Context ctx) {
        return deck.get(ctx, this);
    }

    public void setDeck(List<Card> deck) {
        this.deck.addAll(deck);
    }
}
