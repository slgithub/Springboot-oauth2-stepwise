package com.codehub.explore.oauth2.stepwise.app;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@RestController
@EnableResourceServer
@CommonsLog
public class AuthenticationServer {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServer.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		log.info("AS /user has been called");
		log.info("user info: " + user.toString());
		return user;
	}

}