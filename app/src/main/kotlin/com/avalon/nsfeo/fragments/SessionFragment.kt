package com.avalon.nsfeo.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.avalon.nsfeo.R
import com.avalon.nsfeo.util.DialogFactory
import com.avalon.nsfeo.util.text
import com.gc.materialdesign.views.Button

public class SessionFragment: Fragment() {

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View {

		// Required variables for most operations
		val ctx = this.getActivity()
		val manager = this.getFragmentManager()

		// Inflate the view and set component behaviors
		val v = inflater!!.inflate(R.layout.session, container, false)
		with (v) {

			val email_address = (this.findViewById(R.id.email_address) as EditText)
			val password = (this.findViewById(R.id.password) as EditText)

			(this.findViewById(R.id.start_session) as Button).setOnClickListener {

				val raw_email = email_address.text.trim()
				val raw_passwd = password.text.trim()

				if (raw_email.isEmpty()) email_address.setError(ctx.getString(R.string.email_address_error))
				else if (raw_passwd.isEmpty()) password.setError(ctx.getString(R.string.password_error))
				else {

					// Configure account
					val dialog = DialogFactory.createProgressDialog(ctx, { dialog: DialogInterface ->


						dialog.dismiss()
					})
					dialog.setOnCancelListener { dialog: DialogInterface ->

						Toast.makeText(ctx, R.string.session_fail, Toast.LENGTH_SHORT).show()
						dialog.dismiss()
					}
					dialog.setIndeterminate(true)
					dialog.setMessage(ctx.getString(R.string.starting_session))
					dialog.show()
				}
			}

			// Set the toolbar
			val toolbar = (this.findViewById(R.id.toolbar) as Toolbar)
			with ((ctx as ActionBarActivity)) {

				this.setSupportActionBar(toolbar)
				this.getSupportActionBar().setDisplayShowTitleEnabled(false)
			}
		}

		return v
	}

}
