package com.avalon.backend.beans.cards;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Tonaz on 4/9/2015.
 *
 * Modifier should be an script (Javascript or Java) that the app would execute
 *
 */
public class GrayCard extends Card {

    private String modifier;

    public GrayCard(Entity entity){
        super(entity);
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public Entity toEntity(){
        Entity e = new Entity("GrayCard");
        e.setProperty("Modifier", this.getModifier());
        return this.toEntity(e);
    }

}
