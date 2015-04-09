package com.avalon.backend.beans;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class State {

    private long ID;
    private String name;
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
