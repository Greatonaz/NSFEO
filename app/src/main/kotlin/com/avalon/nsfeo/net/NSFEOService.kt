package com.avalon.nsfeo.net

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.avalon.backend.nsfeo.Nsfeo as NSFEOBackend
import android.content.Context
import android.content.Intent
import android.app.Service
import android.os.IBinder
import android.os.Binder
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory

public class NSFEOService: Service() {

	private companion object {

		val WEB_CLIENT_ID: String = "137906636555-jdq0r7bd2d8ar30gsepk6k262f0g2cs6.apps.googleusercontent.com"

	}
	private inner class NSFEOBinder: Binder()
	// Recall we carry an instance of the outer class: use "with (this@NSFEOService) { ... }" for simplicity

	private val binder: NSFEOBinder = NSFEOBinder()
	private var session: GoogleAccountCredential? = null

	override fun onStartCommand(i: Intent, flags: Int, start_id: Int): Int {

		// TODO: Do something interesting here
		return super.onStartCommand(i, flags, start_id)
	}
	override fun onBind(i: Intent): NSFEOBinder { return this.binder }

	public fun loginByCredentials(ctx: Context, email: String) {

		// Create credentials for Google to authenticate (hopefully)
		val credential = GoogleAccountCredential.usingAudience(ctx, "server:client_id:${NSFEOService.WEB_CLIENT_ID}")
		credential.setSelectedAccountName(email)

		with (NSFEOBackend.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory(), credential).build()) {


		}
	}

}
