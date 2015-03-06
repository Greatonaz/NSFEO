package com.avalon.studios.nsfeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;

import com.avalon.studios.nsfeo.fragments.LoginFragment;
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

	public final static String
		LOGIN_TRANSACTION_TAG = "NSFEOActivity.this::add R.id.container, LoginFragment"
	;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.container);

	    // Register our local database models
        this.initDatabaseManager(
            Card.class,
            UserSession.class,
            User.class,
            Deck.class,
            GameSession.class
        );

	    // Select initial fragment according to application state
	    final FragmentManager manager = this.getSupportFragmentManager();

        if (UserSession.objects(this).isEmpty()) {

	        final Fragment login = new LoginFragment();
	        manager.beginTransaction()
			       .add(R.id.container, login, NSFEOActivity.LOGIN_TRANSACTION_TAG)
			       .commit();
        }
        else {

            // TODO: There has been at least one user for this app, load it and show the main menu fragment
        }

	    // Flush the state right now, as to not let the user hanging around
	    manager.executePendingTransactions();
    }

    @SafeVarargs
    private final void initDatabaseManager(Class<? extends Model>... models) {

        DatabaseAdapter.setDatabaseName("NSFEO_DB");
        final DatabaseAdapter adapter = DatabaseAdapter.getInstance(this);

        final List<Class<? extends Model>> models_list = new ArrayList<>();
        Collections.addAll(models_list, models);
        adapter.setModels(models_list);
    }

}
