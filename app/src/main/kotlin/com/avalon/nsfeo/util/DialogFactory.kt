package com.avalon.nsfeo.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener

public object DialogFactory {

	public fun createProgressDialog(ctx: Context, l: (DialogInterface) -> Unit): ProgressDialog {

		val dialog = ProgressDialog(ctx)
		dialog.setCancelable(true)
		dialog.setOnCancelListener { d: DialogInterface -> d.dismiss() }
		dialog.setOnShowListener(l)

		return dialog
	}

}
