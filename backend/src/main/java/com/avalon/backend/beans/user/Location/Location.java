package com.avalon.backend.beans.user.Location;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Location {

    private String id;
    private String name;

    public Location(){

    }

    public Location(Entity entity){
        this.setId((String) entity.getProperty("Id"));
        this.setName((String) entity.getProperty("Name"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity toEntity(){

        Entity entity = new Entity("Location");

        entity.setProperty("Id",this.getId());
        entity.setProperty("Name", this.getName());

        return entity;
    }
}
