package com.avalon.nsfeo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.avalon.nsfeo.fragments.GameDashboardFragment;
import com.avalon.nsfeo.fragments.SessionFragment;
import com.avalon.nsfeo.model.Card;
import com.avalon.nsfeo.model.Deck;
import com.avalon.nsfeo.model.GameSession;
import com.avalon.nsfeo.model.User;
import com.avalon.nsfeo.model.UserSession;
import com.avalon.nsfeo.net.NSFEOService;
import com.avalon.nsfeo.net.NSFEOServiceConnection;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NSFEOActivity extends ActionBarActivity {

	public final static String LOGIN_TRANSACTION_TAG = "NSFEOActivity.this::add R.id.container, SessionFragment";
	public final static String DASHBOARD_TRANSACTION_TAG = "NSFEOActivity.this::add R.id.container, GameDashboardFragment";

	@Override
	protected void onCreate(final Bundle saved_instance) {

		super.onCreate(saved_instance);
		this.setContentView(R.layout.container);

		// Initialize database functionality
		this.initDatabase(
			Card.class,
			UserSession.class,
			User.class,
			Deck.class,
			GameSession.class
		);

		// Initialize (if not available) our trusty service
		final Intent i = new Intent(this, NSFEOService.class);
		final NSFEOServiceConnection connect = new NSFEOServiceConnection();
		this.bindService(i, connect, Context.BIND_AUTO_CREATE);

		final FragmentManager manager = this.getSupportFragmentManager();
		if (!connect.getService().hasLoggedInUser()) {

			final Fragment f = new SessionFragment();
			manager.beginTransaction()
				   .add(R.id.container, f, NSFEOActivity.LOGIN_TRANSACTION_TAG)
				   .commit();
		}
		else {

			final Fragment f = new GameDashboardFragment();
			manager.beginTransaction()
					.add(R.id.container, f, NSFEOActivity.DASHBOARD_TRANSACTION_TAG)
					.commit();
		}

		manager.executePendingTransactions();
	}

	@SafeVarargs
	private final void initDatabase(Class<? extends Model> ... models) {

		DatabaseAdapter.setDatabaseName("NSFEO_DB");
		final DatabaseAdapter adapter = DatabaseAdapter.getInstance(this);

		final List<Class<? extends Model>> models_list = new ArrayList<>();
		Collections.addAll(models_list, models);
		adapter.setModels(models_list);
	}

}
