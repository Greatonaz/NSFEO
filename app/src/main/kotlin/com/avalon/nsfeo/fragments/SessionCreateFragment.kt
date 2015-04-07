package com.avalon.nsfeo.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.avalon.nsfeo.util.DialogFactory
import com.avalon.nsfeo.R

public class SessionCreateFragment: Fragment() {

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View {

		// Required variables for most operations
		val ctx = this.getActivity()
		val manager = this.getFragmentManager()

		// Inflate the view and set component behaviors
		val v = inflater!!.inflate(R.layout.session_create, container, false)
		with (v) {

			// Enable the links in the disclaimer section
			(this.findViewById(R.id.disclaimer) as TextView).setMovementMethod(LinkMovementMethod.getInstance())

			// Get references to the email and password fields for later use
			val email = (this.findViewById(R.id.email) as EditText)
			val password = (this.findViewById(R.id.password) as EditText)

			// Set the button listener: attempt to connect to the App Engine backend to create user and log him/her in
			(this.findViewById(R.id.create_account) as Button).setOnClickListener { v: View ->

				val email_raw = email.getText().toString().trim()
				val password_raw = password.getText().toString().trim()

				if (email_raw.isEmpty()) email.setError(ctx.getString(R.string.email_empty))
				else if (password_raw.isEmpty()) password.setError(ctx.getString(R.string.password_empty))
				else {

					val dialog = DialogFactory.createProgressDialog(ctx) { dialog: DialogInterface ->

						// TODO: Connect to App Engine backend to create and sign in user
						// Method should receive provided credentials and return the newly created user information and access tokens
						// Register adequate access tokens in the app
						// Then, proceed to the main screen

						dialog.dismiss()
					}

					dialog.setIndeterminate(true)
					dialog.setMessage(ctx.getString(R.string.creating_account))
					dialog.setOnCancelListener { d: DialogInterface -> Toast.makeText(ctx, R.string.create_fail, Toast.LENGTH_SHORT).show() }
					dialog.show()
				}
			}
		}

		return v
	}

}
