package com.avalon.nsfeo.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;

/**
 * Created by Tonaz on 3/3/2015.
 */
public class Card extends Model {

    protected CharField contenido = new CharField(100);

    public static QuerySet<Card> objects(Context ctx) { return Model.objects(ctx, Card.class); }

    public Card(){
        super();
    }

    public String getContenido() {
        return contenido.get();
    }

    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }
}
