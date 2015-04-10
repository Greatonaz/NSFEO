package com.avalon.backend.beans.cards;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PropertyContainer;

/**
 * Created by Tonaz on 4/8/2015.
 */
public class BlackCard extends Card {

    private int requirements;

    public BlackCard(Entity entity){

        super((PropertyContainer) entity.getProperty("Card"));
    }

    public int getRequirements() {
        return requirements;
    }

    public void setRequirements(int requirements) {
        this.requirements = requirements;
    }

    @Override
    public Entity toEntity() {

        Entity e = new Entity("BlackCard");
        e.setProperty("Requirements", this.getRequirements());
        return this.toEntity(e);
    }
}
