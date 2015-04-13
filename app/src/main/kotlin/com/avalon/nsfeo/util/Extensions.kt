package com.avalon.nsfeo.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.EditText

public val EditText.text: String
	get() = this.getText().toString()

public fun Context.postServiceTask(i: Intent, flags: Int, task: ServiceConnection.(IBinder?) -> Unit): Boolean {

	return this.bindService(i, object: ServiceConnection {

		override fun onServiceDisconnected(name: ComponentName?) = this.task(null)
		override fun onServiceConnected(name: ComponentName?, service: IBinder?) = this.task(service)

	}, flags)
}
