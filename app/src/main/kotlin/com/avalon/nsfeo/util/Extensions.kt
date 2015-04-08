package com.avalon.nsfeo.util

import android.widget.EditText

public val EditText.text: String
	get() = this.getText().toString()
