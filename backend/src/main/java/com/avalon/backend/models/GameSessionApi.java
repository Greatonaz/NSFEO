package com.avalon.backend.models;

import com.avalon.backend.NSFEOGoogleBackend;
import com.avalon.backend.beans.cards.WhiteCard;
import com.avalon.backend.beans.gamesession.GameSession;
import com.avalon.backend.beans.gamesession.Player;
import com.avalon.backend.beans.gamesession.Round;
import com.avalon.backend.beans.user.GameUser;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */

@Api(
        name = "session",
        version = "v1",
        clientIds = { NSFEOGoogleBackend.Constants.WEB_CLIENT_ID, NSFEOGoogleBackend.Constants.ANDROID_CLIENT_ID },
        audiences = { NSFEOGoogleBackend.Constants.ANDROID_AUDIENCES },
        namespace = @ApiNamespace(
                ownerDomain = "backend.avalon.com",
                ownerName = "backend.avalon.com",
                packagePath = ""
        )
)
public class GameSessionApi {

    @ApiMethod(name = "session.create")
    public GameSession createNewGameSession(final User user, final @Named("session_name") String session_name,
                                       final @Named("round_limit") int round_limit) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            GameSession gameSession = new GameSession(session_name, round_limit);

            datastoreService.put(gameSession.toEntity());
            txn.commit();

            return gameSession;

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }

    }

    @ApiMethod(name = "session.invite")
    public Object inviteToGameSession(final User user, final Key session, final @Named("email_address") String email) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Filter filter = new FilterPredicate("Email", Query.FilterOperator.EQUAL, email);
            Query query = new Query("GameUser").setFilter(filter);

            Entity result = datastoreService.prepare(query).asSingleEntity();
            Player player = new Player(new GameUser(result));
            datastoreService.put(player.toEntity());

            query = new Query("GameSession").setFilter(new FilterPredicate("Id", Query.FilterOperator.EQUAL, session));
            result = datastoreService.prepare(query).asSingleEntity();
            GameSession gameSession = new GameSession(result);
            gameSession.addPlayer(player);

            datastoreService.put(gameSession.toEntity());
            txn.commit();

            return true;
        } finally {
            if (txn.isActive()) { txn.rollback(); }
        }
    }

    @ApiMethod(name = "session.join")
    public GameSession joinActiveSession(final User user, final @Named("email_address") String email, final Key session) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Filter filter;
            Query query;
            Entity result;

            query = new Query("GameSession").setFilter(new FilterPredicate("Id", Query.FilterOperator.EQUAL, session));
            result = datastoreService.prepare(query).asSingleEntity();
            GameSession gameSession = new GameSession(result);

            for(Player player: gameSession.getPlayers()){

                if(player.getGameUser().getEmail().equalsIgnoreCase(email)){
                    player.setHasJoined(true);
                    datastoreService.put(player.toEntity());
                }

            }

            datastoreService.put(gameSession.toEntity());
            txn.commit();

            return gameSession;

        } finally {

            if (txn.isActive()) {
                txn.rollback();
            }

        }
    }

    @ApiMethod(name = "session.leave")
    public GameSession leaveActiveSession(final User user,  final @Named("email_address") String email, final Key session) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        try {

            Filter filter;
            Query query;
            Entity result;

            query = new Query("GameSession").setFilter(new FilterPredicate("Id", Query.FilterOperator.EQUAL, session));
            result = datastoreService.prepare(query).asSingleEntity();
            GameSession gameSession = new GameSession(result);

            for(Player player: gameSession.getPlayers()){

                if(player.getGameUser().getEmail().equalsIgnoreCase(email)){
                    gameSession.removePlayer(player);
                }

            }

            datastoreService.put(gameSession.toEntity());
            txn.commit();

            return gameSession;

        } finally {

            if (txn.isActive()) {
                txn.rollback();
            }

        }
    }

    @ApiMethod(name = "session.play")
    public GameSession playRoundCard(final User user, final Key session, final @Named("cards") List<Integer> cardIndexes, final @Named("email_address") String email) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Entity result;

            // Get the GameSession
            Query query = new Query("GameSession").setFilter(new FilterPredicate("Id", Query.FilterOperator.EQUAL, session));
            result = datastoreService.prepare(query).asSingleEntity();
            GameSession gameSession = new GameSession(result);

            // Find the player
            Player player = GameSessionManager.findPlayer(gameSession.getPlayers(), email);

            if(player == null){
                // THIS SHOULD NEVER HAPPEN
                return null;
            }

            // Get the cards the player will submit and take them out of the player's hand
            List<WhiteCard> cards = GameSessionManager.getCards(cardIndexes, player);
            GameSessionManager.removeFromHand(cards, player);

            // Submit them
            Round round = gameSession.getCurrentRound();
            round.addSubmission(player, cards);

            // Verify if the round is ready
            boolean isRoundReady = GameSessionManager.isRoundReady(gameSession.getPlayers(), round.getSubmissions());
            round.setReady(isRoundReady);

            // Deal cards to player and remove dealt cards from deck
            int cardsPlayed = cards.size();
            GameSessionManager.dealNewCards(cardsPlayed, gameSession.getDeck(), player);

            // Store everything
            datastoreService.put(player.toEntity());
            datastoreService.put(gameSession.toEntity());
            txn.commit();

            return gameSession;

        } finally {

            if (txn.isActive()) {
                txn.rollback();
            }

        }
    }

    @ApiMethod(name = "session.judge")
    public Object selectRoundWinner(final User user, final @Named("session") int session, final @Named("player") int playerIndex) {


        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Entity result;

            // Get the GameSession
            Query query = new Query("GameSession").setFilter(new FilterPredicate("Id", Query.FilterOperator.EQUAL, session));
            result = datastoreService.prepare(query).asSingleEntity();
            GameSession gameSession = new GameSession(result);

            // Find the player
            Round round = gameSession.getCurrentRound();
            round.setWinner(playerIndex);

            Player player = gameSession.getPlayers().get(playerIndex);
            player.incrementPoints();

            // Push Notifications

            // Increment current round
            gameSession.moveToNextRound();

            // Store everything
            datastoreService.put(round.toEntity());
            datastoreService.put(player.toEntity());
            datastoreService.put(gameSession.toEntity());

            txn.commit();

            return gameSession;

        } finally {

            if (txn.isActive()) {
                txn.rollback();
            }

        }

    }

}
