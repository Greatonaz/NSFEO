package com.avalon.studios.nsfeo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;

import com.avalon.studios.nsfeo.model.Card;
import com.avalon.studios.nsfeo.model.Deck;
import com.avalon.studios.nsfeo.model.GameSession;
import com.avalon.studios.nsfeo.model.User;
import com.avalon.studios.nsfeo.model.UserSession;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import old.nsfeo.R;

public class NSFEOActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_blank);

        this.initDatabaseManager(
            Card.class,
            UserSession.class,
            User.class,
            Deck.class,
            GameSession.class
        );

        final FrameLayout layout = (FrameLayout) (this.findViewById(R.id.container));

        if (UserSession.objects(this).isEmpty()) {
            // TODO: Create login fragment and show this
        }
        else {

            // TODO: There has been at least one user for this app, load it and show the main menu fragment
        }
    }

    private void initDatabaseManager(Class<? extends Model> ... models) {

        DatabaseAdapter.setDatabaseName("NSFEO_DB");
        final DatabaseAdapter adapter = DatabaseAdapter.getInstance(this);

        final List<Class<? extends Model>> models_list = new ArrayList<>();
        Collections.addAll(models_list, models);
        adapter.setModels(models_list);
    }

}
