package com.avalon.backend.models;

import com.avalon.backend.NSFEOGoogleBackend;
import com.avalon.backend.beans.GameUser;
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
    public Object createNewGameSession(final User user, final @Named("session_name") String session_name) {
        return null;
    }

    @ApiMethod(name = "session.active")
    public List<Object> listActiveGameSessions(final User user) {

        return Collections.emptyList();

    }

    private GameUser getUser(Key user_key){

        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query(user_key);
        Entity result = datastoreService.prepare(query).asSingleEntity();

        ArrayList<TaskBean> taskBeans = new ArrayList<TaskBean>();
        for (Entity result : results) {
            TaskBean taskBean = new TaskBean();
            taskBean.setId(result.getKey().getId());
            taskBean.setData((String) result.getProperty("data"));
            taskBeans.add(taskBean);
        }

    }
}
