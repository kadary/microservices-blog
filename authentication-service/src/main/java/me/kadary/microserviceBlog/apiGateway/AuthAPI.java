package me.kadary.microserviceBlog.apiGateway;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.kadary.microserviceBlog.userService.User;
import me.kadary.microserviceBlog.userService.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;


@Named
@Path("/service")
public class AuthAPI {
	
	@Autowired
	private UserRepository repository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User home(@AuthenticationPrincipal UserDetails userDetails) { 
		return this.repository.findByUsername(userDetails.getUsername());
	}
}
