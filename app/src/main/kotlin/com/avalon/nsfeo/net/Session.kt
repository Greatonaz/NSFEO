package com.avalon.nsfeo.net

import com.avalon.nsfeo.model.User
import android.content.Context

public object Session {

	public fun hasValidSession(ctx: Context): Boolean {

		// TODO: Consider adding a session token and filter from there, as that would tell us which user has been signed in
		// TODO: Change this validation to the new one
		return !User.objects(ctx).isEmpty()
	}

}
