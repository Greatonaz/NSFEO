package com.avalon.backend.beans;

import java.util.List;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class InterestGroup {

    private String name;
    private List<CardAffinity> affinityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CardAffinity> getAffinityList() {
        return affinityList;
    }

    public void setAffinityList(List<CardAffinity> affinityList) {
        this.affinityList = affinityList;
    }
}
