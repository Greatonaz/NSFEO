package com.avalon.nsfeo.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.avalon.nsfeo
import com.avalon.nsfeo.R
import com.avalon.nsfeo.model.User
import com.avalon.nsfeo.net.AppEngine
import com.avalon.nsfeo.util.DialogFactory
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import com.avalon.backend.appEngineNSFEO.model as model

public class SessionLoginFragment: Fragment() {

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View {

		// Required variables for most operations
		val ctx = this.getActivity()
		val manager = this.getFragmentManager()

		// Inflate the view and set component behaviors
		val v = inflater!!.inflate(nsfeo.R.layout.session_login, container, false)
		with (v) {

			// Get references to the email and password fields for later use
			val email = (this.findViewById(R.id.email) as EditText)
			val password = (this.findViewById(R.id.password) as EditText)

			// Set the button listener: attempt to connect to the App Engine backend to login user
			(this.findViewById(R.id.login) as Button).setOnClickListener { v: View ->

				val email_raw = email.getText().toString().trim()
				val password_raw = password.getText().toString().trim()

				if (email_raw.isEmpty()) email.setError(ctx.getString(R.string.email_empty))
				else if (password_raw.isEmpty()) password.setError(ctx.getString(R.string.password_empty))
				else {

					val dialog = DialogFactory.createProgressDialog(ctx) { dialog: DialogInterface ->

						// TODO: Connect to App Engine backend to get session information
						// Login the user into the system and register adequate access tokens in the app
						// Then, proceed to the main screen
						val api_result = AppEngine.Task<User?> {

							val gae_user: model.User = this.loginUser(email_raw, "").execute()
							var user = User()

							return@Task null

						}.get(5000L, TimeUnit.MILLISECONDS)

						dialog.dismiss()
					}

					dialog.setIndeterminate(true)
					dialog.setMessage(ctx.getString(R.string.logging_in))
					dialog.setOnCancelListener { d: DialogInterface -> Toast.makeText(ctx, R.string.login_failure, Toast.LENGTH_SHORT).show() }
					dialog.show()
				}
			}
		}

		return v
	}

}
