package com.avalon.backend.beans.cards;

import com.avalon.backend.beans.user.GameUser;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PropertyContainer;

/**
 * Created by Tonaz on 4/8/2015.
 */
public abstract class Card {

    private String id;
    private String content;
    private GameUser author;
    private String theme;
    private String language;

    public Card(PropertyContainer entity) {
        this.setId((String) entity.getProperty("Id"));
        this.setContent((String) entity.getProperty("Content"));
        this.setAuthor((GameUser)entity.getProperty("Author"));
        this.setTheme((String) entity.getProperty("Theme"));
        this.setLanguage((String) entity.getProperty("Language"));
    }

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

    public abstract Entity toEntity();
    protected Entity toEntity(Entity e) {

        EmbeddedEntity entity = new EmbeddedEntity();
        entity.setProperty("Id", this.getId());
        entity.setProperty("Content", this.getContent());
        entity.setProperty("Author", this.getAuthor());
        entity.setProperty("Theme", this.getTheme());
        entity.setProperty("Language", this.getLanguage());

        e.setProperty("Card", entity);
        return e;
    }
}
