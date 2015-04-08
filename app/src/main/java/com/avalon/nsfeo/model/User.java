package com.avalon.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.field.ManyToManyField;

import java.util.List;

/**
 * Created by Tonaz on 3/3/2015.
 */
public class User extends Model {

    protected ForeignKeyField<UserSession> userSession = new ForeignKeyField<>(UserSession.class);
    protected ManyToManyField<User, User> friendList = new ManyToManyField<>(User.class, User.class);
    protected ManyToManyField<User, GameSession> gameSessionList = new ManyToManyField<>(User.class, GameSession.class);
    protected CharField profilePicPath = new CharField(255);

    public static QuerySet<User> objects(Context ctx) { return Model.objects(ctx, User.class); }

    public User(){
        super();
    }

    public UserSession getUserSession(Context ctx) {
        return userSession.get(ctx);
    }

    public void setUserSession(UserSession userSession) {
        this.userSession.set(userSession);
    }

    public QuerySet<User> getFriendList(Context ctx) {
        return friendList.get(ctx, this);
    }

    public void setFriendList(List<User> friendList) {
        this.friendList.addAll(friendList);
    }

    public QuerySet<GameSession> getGameSessionList(Context ctx) {
        return gameSessionList.get(ctx, this);
    }

    public void setGameSessionList(List<GameSession> gameSessionList) {
        this.gameSessionList.addAll(gameSessionList);
    }

    public String getProfilePicPath() {
        return profilePicPath.get();
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath.set(profilePicPath);
    }
}
