package com.avalon.nsfeo.net

import android.content.Context
import android.os.AsyncTask
import com.avalon.backend.appEngineNSFEO.AppEngineNSFEO
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.extensions.android.json.AndroidJsonFactory

public class AppEngine {

	public companion object {

		public val CONNECT_URL: String = "http://10.0.2.2/_aq/api"

	}

	public object Task {

		public fun invoke<T>(task: AppEngineNSFEO.() -> T): AsyncTask<Nothing, Nothing, T> {

			val async = object: AsyncTask<Nothing, Nothing, T>() {

				override fun doInBackground(vararg params: Nothing?): T {

					val api = AppEngineNSFEO.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory(), null)
											.build()

					val result = api.task()
					return result
				}

			}
			return async.execute()
		}

	}

}
