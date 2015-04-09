package com.avalon.backend.beans.user.Location;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class State {

    private long id;
    private String name;
    private Location location;

    public State(){

    }

    public State(Entity entity){
        this.setId((Long)entity.getProperty("Id"));
        this.setName((String) entity.getProperty("Name"));
        this.setLocation((Location) entity.getProperty("Location"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Entity toEntity(){

        Entity entity = new Entity("State");
        entity.setProperty("Id", this.getId());
        entity.setProperty("Name", this.getName());
        entity.setProperty("Location", this.getLocation());

        return entity;

    }

}