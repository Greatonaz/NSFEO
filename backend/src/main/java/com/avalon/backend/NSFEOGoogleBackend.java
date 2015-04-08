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

	}

	// TODO: These are not there yet, complete them with valid types
	// TODO: Create intermediate GAE representations for our database model (must work on that one too)

	@ApiMethod(name = "session.active")
	public List<Object> listActiveGameSessions(final User user) {
		return Collections.emptyList();
	}
	@ApiMethod(name = "session.create")
	public Object createNewGameSession(final User user, final @Named("session_name") String session_name) {
		return null;
	}
	@ApiMethod(name = "session.invite")
	public Object inviteToGameSession(final User user, final @Named("guest") int guest) {
		return null;
	}
	@ApiMethod(name = "session.join")
	public Object joinActiveSession(final User user, final @Named("session") int session) {
		return null;
	}
	@ApiMethod(name = "session.leave")
	public Object leaveActiveSession(final User user, final @Named("session") int session) {
		return null;
	}
	@ApiMethod(name = "session.play")
	public Object playRoundCard(final User user, final @Named("session") int session, final @Named("card") int card) {
		return null;
	}
	@ApiMethod(name = "session.judge")
	public Object selectRoundWinner(final User user, final @Named("session") int session, final @Named("card") int card) {
		return null;
	}

}
