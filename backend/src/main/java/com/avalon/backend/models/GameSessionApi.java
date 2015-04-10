package com.avalon.backend.models;

import com.avalon.backend.NSFEOGoogleBackend;
import com.avalon.backend.beans.gamesession.GameSession;
import com.avalon.backend.beans.gamesession.Player;
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

            Key newGameSessionKey = KeyFactory.createKey("TaskBeanParent", "todo.txt");
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
    public Object joinActiveSession(final User user, final @Named("session") int session) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        return null;
    }
    @ApiMethod(name = "session.leave")
    public Object leaveActiveSession(final User user, final @Named("session") int session) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        return null;
    }
    @ApiMethod(name = "session.play")
    public Object playRoundCard(final User user, final @Named("session") int session, final @Named("card") int card) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        return null;
    }
    @ApiMethod(name = "session.judge")
    public Object selectRoundWinner(final User user, final @Named("session") int session, final @Named("card") int card) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        return null;
    }

}