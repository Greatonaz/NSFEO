package com.avalon.backend.models;

import com.avalon.backend.NSFEOGoogleBackend;
import com.avalon.backend.beans.Player;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;

import java.util.Collections;
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
    public Object createNewGameSession(final User user, final @Named("session_name") String session_name,
                                       final @Named("round_limit") int round_limit) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Key newGameSessionKey = KeyFactory.createKey("TaskBeanParent", "todo.txt");
            Entity gameSessionEntity = new Entity("GameSession", newGameSessionKey.getId(), newGameSessionKey);

            gameSessionEntity.setProperty("Name", session_name);
            gameSessionEntity.setProperty("RoundLimit", round_limit);
            gameSessionEntity.setProperty("CurrentRound", 0);
            gameSessionEntity.setProperty("JudgeIndex", 0);
            gameSessionEntity.setProperty("IsSessionReady", false);

            datastoreService.put(gameSessionEntity);
            txn.commit();

            return gameSessionEntity;

        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }

    }

    @ApiMethod(name = "session.invite")
    public Object inviteToGameSession(final User user, final @Named("guest") int guest) {

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();

        try {

            Key gameSessionKey = KeyFactory.createKey("GameSessionParent", "asalieri");
            Query query = new Query(gameSessionKey);

            Entity result = datastoreService.prepare(query).asSingleEntity();
            List<Player> playerList = (List<Player>)result.getProperty("players");
            Player player = new Player();
            /*player.setGameUser();
            playerList.add()*/
            //updatedEmployee.addProperty(makeProperty("nickname", makeValue(newNickname)));

            txn.commit();

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
