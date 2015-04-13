package com.avalon.nsfeo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.avalon.nsfeo.fragments.GameDashboardFragment;
import com.avalon.nsfeo.fragments.SessionFragment;
import com.avalon.nsfeo.model.Card;
import com.avalon.nsfeo.model.Deck;
import com.avalon.nsfeo.model.GameSession;
import com.avalon.nsfeo.model.User;
import com.avalon.nsfeo.model.UserSession;
import com.avalon.nsfeo.net.NSFEOService;
import com.avalon.nsfeo.net.NSFEOService.NSFEOBinder;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NSFEOActivity extends ActionBarActivity {

	public final static String LOGIN_TRANSACTION_TAG = "NSFEOActivity.this::add R.id.container, SessionFragment";
	public final static String DASHBOARD_TRANSACTION_TAG = "NSFEOActivity.this::add R.id.container, GameDashboardFragment";

	private final ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {

			final FragmentManager manager = NSFEOActivity.this.getSupportFragmentManager();

			Log.d("NSFEO Service", "Bound to service");
			final NSFEOService service = ((NSFEOBinder) (binder)).getService();

			if (!service.hasLoggedInUser()) {

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

			NSFEOActivity.this.unbindService(this);
			manager.executePendingTransactions();
		}
		@Override
		public void onServiceDisconnected(ComponentName name) { Log.d("NSFEO Service", "Unbound from service"); }

	};

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
		final FragmentManager manager = this.getSupportFragmentManager();
		this.startService(new Intent(this, NSFEOService.class));

		// Bind to the service and get the current login status
		this.bindService(new Intent(this, NSFEOService.class), this.connection, Context.BIND_AUTO_CREATE);
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
