package com.avalon.nsfeo.net

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

public class NSFEOService: Service() {

	private inner class NSFEOBinder: Binder() {

		// TODO: Do something interesting here
		// Recall we carry an instance of the outer class: use "with (this@NSFEOService) { ... }" for simplicity

	}

	private val binder: NSFEOBinder = NSFEOBinder()

	override fun onStartCommand(i: Intent, flags: Int, start_id: Int): Int {

		// TODO: Do something interesting here
		return super.onStartCommand(i, flags, start_id)
	}
	override fun onBind(i: Intent): NSFEOBinder { return this.binder }

}
