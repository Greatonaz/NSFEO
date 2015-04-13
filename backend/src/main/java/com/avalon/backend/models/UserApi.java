package com.avalon.backend.models;

import com.avalon.backend.NSFEOGoogleBackend;
import com.avalon.backend.beans.gamesession.GameSession;
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
import com.google.appengine.api.users.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */

@Api(
        name = "user",
        version = "v1",
        clientIds = { NSFEOGoogleBackend.Constants.WEB_CLIENT_ID, NSFEOGoogleBackend.Constants.ANDROID_CLIENT_ID },
        audiences = { NSFEOGoogleBackend.Constants.ANDROID_AUDIENCES },
        namespace = @ApiNamespace(
                ownerDomain = "backend.avalon.com",
                ownerName = "backend.avalon.com",
                packagePath = ""
        )
)
public class UserApi {

    @ApiMethod(name = "user.getFriendList")
    public Object getFriendList(final User user) {
        // @TODO
        Key newGameSessionKey = KeyFactory.createKey("GameSession", user.getEmail());
        GameUser gameUser = this.getUser(newGameSessionKey);

        return gameUser.getFriends();
    }

    @ApiMethod(name = "user.sessions")
    public List<GameSession> listActiveGameSessions(final User user) {

        // @TODO
        Key newGameSessionKey = KeyFactory.createKey("GameSession", user.getEmail());
        GameUser gameUser = this.getUser(newGameSessionKey);

        return gameUser.getSessions();

    }

    private GameUser getUser(Key user_key){

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(user_key);
        Entity result = datastoreService.prepare(query).asSingleEntity();

        GameUser user = new GameUser(result);
        return user;

    }
}
