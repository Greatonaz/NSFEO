package com.avalon.nsfeo.net

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

public class NSFEOServiceConnection: ServiceConnection {

	private var service: NSFEOService? = null

	public fun getService(): NSFEOService = this.service!!

	override fun onServiceDisconnected(name: ComponentName?) { this.service = null }
	override fun onServiceConnected(name: ComponentName?, service: IBinder?) { this.service = (service as NSFEOService.NSFEOBinder).getService() }

}
