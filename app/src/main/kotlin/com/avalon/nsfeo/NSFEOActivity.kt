package com.avalon.nsfeo

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import com.avalon.nsfeo.fragments.SessionFragment
import com.avalon.nsfeo.model.*
import com.avalon.nsfeo.session.Session
import com.orm.androrm.DatabaseAdapter
import com.orm.androrm.Model
import old.nsfeo.R
import java.util.ArrayList
import java.util.Collections

public class NSFEOActivity: ActionBarActivity() {

	private companion object {
		val LOGIN_TRANSACTION_TAG: String = "NSFEOActivity.this::add R.id.container, LoginFragment"
	}

	override fun onCreate(saved_instance: Bundle?) {

		super.onCreate(saved_instance)
		this.setContentView(R.layout.container)

		// Prepare the environment to connect models to the underlying implementation
		this.initDatabase(
			javaClass<Card>(),
			javaClass<UserSession>(),
			javaClass<User>(),
			javaClass<Deck>(),
			javaClass<GameSession>()
		)

		// Check if there are users, or if a user has been signed in already
		with (this.getSupportFragmentManager()) {

			if (!Session.hasValidSession(this@NSFEOActivity)) {

				val f = SessionFragment()
				this.beginTransaction()
						.add(R.id.container, f, NSFEOActivity.LOGIN_TRANSACTION_TAG)
						.commit()
			}
			else {
				;
			}

			this.executePendingTransactions()
		}
	}

	SafeVarargs
	private fun initDatabase(vararg models: Class<out Model>) {

		DatabaseAdapter.setDatabaseName("NSFEO_DB")
		val adapter = DatabaseAdapter.getInstance(this)

		val models_list = ArrayList<Class<out Model>>()
		Collections.addAll<Class<out Model>>(models_list, *models)
		adapter.setModels(models_list)
	}

}
