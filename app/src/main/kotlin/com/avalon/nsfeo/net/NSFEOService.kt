package com.avalon.nsfeo.net

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import com.avalon.nsfeo.model.User
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import org.apache.commons.codec.digest.DigestUtils
import com.avalon.backend.nsfeo.Nsfeo as NSFEOBackend

public class NSFEOService: Service() {

	private companion object {

		val WEB_CLIENT_ID: String = "137906636555-jdq0r7bd2d8ar30gsepk6k262f0g2cs6.apps.googleusercontent.com"
		val DEBUG_MODE: Boolean = true
		val DEBUG_EMAIL_ADDRESS: String = "debug@nsfeo.com"
		val DEBUG_PASSWORD_SHA1: String = "ef55ec6ce4979cdb824b410c31de40e667ace61e"  // nsfeo-debug

	}
	public inner class NSFEOBinder(private val service: NSFEOService): Binder() {
		public fun getService(): NSFEOService = this.service
	}
	// Recall we carry an instance of the outer class: use "with (this@NSFEOService) { ... }" for simplicity

	private val binder: NSFEOBinder
	private var user: User? = null

	init { this.binder = NSFEOBinder(this) }

	override fun onStartCommand(i: Intent?, flags: Int, start_id: Int): Int = Service.START_STICKY
	override fun onBind(i: Intent): NSFEOBinder { return this.binder }

	public fun hasLoggedInUser(): Boolean = (this.user != null)
	public fun getUser(): User = this.user!!
	public fun loginByCredentials(ctx: Context, email: String, password: String): Boolean {

		// The user is already authenticated: let 'em trough
		if (this.user != null)
			return true

		// TODO: This is for debug purposes only, this will be removed in the final version
		if (NSFEOService.DEBUG_MODE) {

			if (email == NSFEOService.DEBUG_EMAIL_ADDRESS) {

				val password_hash = DigestUtils.shaHex(password)
				if (password_hash == NSFEOService.DEBUG_PASSWORD_SHA1) {

					// Create a debug user and pass it through: if the debug user already exists, just fetch it
					val user = User.objects(ctx).get(1) ?: User(email)
					user.updateLastLogin()
					user.save(ctx)

					this.user = user

					return true
				}
			}
		}

		// Create credentials for Google to authenticate (hopefully)
		val credential = GoogleAccountCredential.usingAudience(ctx, "server:client_id:${NSFEOService.WEB_CLIENT_ID}")
		credential.setSelectedAccountName(email)

		with (NSFEOBackend.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory(), credential).build()) {

			// TODO: Connect the application with the App Engine backend and approve authentication

			return false
		}
	}

}
