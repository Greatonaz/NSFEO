package com.avalon.backend.beans.cards;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Tonaz on 4/9/2015.
 */
public class WhiteCard extends Card{

    public WhiteCard(Entity entity){
        super(entity);
    }

    @Override
    public Entity toEntity(){
        return this.toEntity(new Entity("WhiteCard"));
    }

}
