package com.avalon.backend.models;

import com.avalon.backend.beans.cards.BlackCard;
import com.avalon.backend.beans.cards.WhiteCard;
import com.avalon.backend.beans.gamesession.Player;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tonaz on 4/10/2015.
 */
public class DeckBuilder {

    // Check the cost of this algorithm
    public static List<BlackCard> createBlackDeck(int maxPoints, int numberOfPlayers){

        int cardsRequired = ((maxPoints - 1) * numberOfPlayers) + 1;

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        List<Entity> results;

        // Get the GameSession
        Query query = new Query("BlackCard");
        results = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());

        List<Integer> cardIndexes = Utils.randomIntegerList(0, results.size() - 1, cardsRequired);
        List<BlackCard> blackCardDeck = new LinkedList<>();
        for(Integer index : cardIndexes){
            blackCardDeck.add(new BlackCard(results.get(index)));
        }

        return blackCardDeck;

    }

    // Check the cost of this algorithm
    public static List<WhiteCard> createWhiteDeck(int numberOfRounds, int numberOfPlayers){

        int cardsRequired = (55 + numberOfRounds * 4) * numberOfPlayers;

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        List<Entity> results;

        // Get the GameSession
        Query query = new Query("WhiteCard");
        results = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());

        List<Integer> cardIndexes = Utils.randomIntegerList(0, results.size() - 1, cardsRequired);
        List<WhiteCard> blackCardDeck = new LinkedList<>();
        for(Integer index : cardIndexes){
            blackCardDeck.add(new WhiteCard(results.get(index)));
        }

        return blackCardDeck;

    }

    public static List<BlackCard> createSmartBlackDeck(List<Player> players){
        // To be implemented with 3 artificial intelligences based on the players histories and taste
        return null;
    }

}
