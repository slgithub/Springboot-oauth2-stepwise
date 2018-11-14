/**
 * 
 */
package com.codehub.explore.oauth2.stepwise.app;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

/**
 * @author slgithub
 *
 */
@SpringBootApplication
@RestController
@EnableResourceServer
@CommonsLog
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServer {

	public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class, args);
    }

    private String message = "Hello world!";

    @PreAuthorize("hasRole('ROLE_RS_READ')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, String> home() {
    	log.info("TEST:::home");
        return Collections.singletonMap("message", message);
    }

    @PreAuthorize("hasRole('ROLE_RS_WRITE')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void updateMessage(@RequestBody String message) {
    	log.info("TEST:::updateMessage");
        this.message = message;
    }

    @PreAuthorize("#oauth2.hasScope('resource-server-read')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, String> user(Principal user) {
    	log.info("TEST:::user");
        return Collections.singletonMap("message", "user is: " + user.toString());
    }


}
