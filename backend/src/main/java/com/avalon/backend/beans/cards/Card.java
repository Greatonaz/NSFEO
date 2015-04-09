package com.avalon.backend.beans.cards;

import com.avalon.backend.beans.user.GameUser;

/**
 * Created by Tonaz on 4/8/2015.
 */
public abstract class Card {

    private String id;
    private String content;
    private GameUser author;
    private String theme;
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GameUser getAuthor() {
        return author;
    }

    public void setAuthor(GameUser author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
