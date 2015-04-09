package com.avalon.backend.beans;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class Card {

    private String content;
    private GameUser creator;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GameUser getCreator() {
        return creator;
    }

    public void setCreator(GameUser creator) {
        this.creator = creator;
    }

}
