package com.avalon.backend.beans.user.Location;

import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Country {

    private String id;
    private String name;
    private List<State> states;

    public Country(){

    }

    public Country(Entity entity){
        this.setId((String) entity.getProperty("Id"));
        this.setName((String) entity.getProperty("Name"));
        this.setStates((List<State>) entity.getProperty("States"));
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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Entity toEntity(){
        Entity entity = new Entity("Country");
        entity.setProperty("Id", this.getId());
        entity.setProperty("Name", this.getName());
        entity.setProperty("States", this.getStates());
        return entity;
    }
}
