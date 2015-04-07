package com.avalon.nsfeo.net

import android.app.Service
import android.content.Intent
import android.os.IBinder

public class NSFEOSessionService: Service() {

	override fun onStartCommand(i: Intent, flags: Int, start_id: Int): Int {

		// TODO: Do something useful here.
		return Service.START_STICKY
	}
	override fun onBind(i: Intent): IBinder? {

		// TODO: Do something useful here.
		return null
	}

}
