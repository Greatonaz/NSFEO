package com.avalon.backend.beans;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Location {

    private String name;
    private State state;
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
