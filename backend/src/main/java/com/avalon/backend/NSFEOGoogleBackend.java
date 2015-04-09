package com.avalon.backend;

import com.avalon.backend.NSFEOGoogleBackend.Constants;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.users.User;
import java.util.Collections;
import java.util.List;

@Api(
	name = "nsfeo",
	version = "v1",
	clientIds = { Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID },
	audiences = { Constants.ANDROID_AUDIENCES },
	namespace = @ApiNamespace(
		ownerDomain = "backend.avalon.com",
		ownerName = "backend.avalon.com",
		packagePath = ""
	)
)
public class NSFEOGoogleBackend {

	public static class Constants {

		public final static String WEB_CLIENT_ID = "http://137906636555-jdq0r7bd2d8ar30gsepk6k262f0g2cs6.apps.googleusercontent.com/";
		public final static String ANDROID_CLIENT_ID = "http://137906636555-c8pfldmju8sbbp5q9oobu50rbfbgvhnt.apps.googleusercontent.com/";
		public final static String ANDROID_AUDIENCES = Constants.WEB_CLIENT_ID;
        public final static String GCM_API_KEY = "AIzaSyDywvIbuFfEBcJDtAAVK_TQ3tElUeiW-ZQ";

	}

	// TODO: These are not there yet, complete them with valid types
	// TODO: Create intermediate GAE representations for our database model (must work on that one too)




}
