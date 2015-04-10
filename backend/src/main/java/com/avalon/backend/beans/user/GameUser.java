package com.avalon.backend.beans.user;

import com.avalon.backend.beans.gamesession.GameSession;
import com.avalon.backend.beans.user.Location.Location;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class GameUser {

    private Key id;
    private String email;
    private String name;
    private DateTime date_of_birth;
    private Location location;
    private List<GameSession> sessions;
    private List<GameUser> friends;
    private Profile profile;

    public GameUser(Entity entity){

        this.setId((Key) entity.getProperty("id"));
        this.setEmail((String) entity.getProperty("Email"));
        this.setName((String)entity.getProperty("Name"));
        this.setDateOfBirth(DateTime.parse((String) entity.getProperty("DOB")));
        this.setLocation((Location) entity.getProperty("Location"));
        this.setSessions((List<GameSession>) entity.getProperty("Sessions"));
        this.setFriends((List<GameUser>) entity.getProperty("GameUser"));
        this.setProfile((Profile) entity.getProperty("Profile"));

    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(DateTime date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<GameSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession> sessions) {
        this.sessions = sessions;
    }

    public List<GameUser> getFriends() {
        return friends;
    }

    public void setFriends(List<GameUser> friends) {
        this.friends = friends;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Entity toEntity(){
        Entity entity = new Entity("GameUser");
        entity.setProperty("id", this.getId());
        entity.setProperty("Name", this.getName());
        entity.setProperty("DOB", this.getDateOfBirth());
        entity.setProperty("Location", this.getLocation());
        entity.setProperty("Sessions", this.getSessions());
        entity.setProperty("Profile", this.getProfile());
        return entity;
    }

    public EmbeddedEntity toEmbedded(){

        EmbeddedEntity entity = new EmbeddedEntity();

        entity.setProperty("id", this.getId());
        entity.setProperty("Name", this.getName());
        entity.setProperty("DOB", this.getDateOfBirth());
        entity.setProperty("Location", this.getLocation());
        entity.setProperty("Sessions", this.getSessions());
        entity.setProperty("Profile", this.getProfile());

        return entity;
    }
}
