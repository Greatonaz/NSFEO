package com.avalon.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(
	name = "appEngineNSFEO",
	version = "v1",
	namespace = @ApiNamespace(
		ownerDomain = "backend.avalon.com",
		ownerName = "backend.avalon.com",
		packagePath = ""
	)
)
public class AppEngineBackend {

	@ApiMethod(name = "createUser")
	public User createUser(final @Named("email") String email, final @Named("password") String password) {

		// TODO: Create a real user
		return new User();
	}
	@ApiMethod(name = "loginUser")
	public User loginUser(final @Named("email") String email, final @Named("password") String password) {

		// TODO: Create a real user
		return new User();
	}

}
