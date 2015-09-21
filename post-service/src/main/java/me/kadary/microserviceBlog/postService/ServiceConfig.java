package me.kadary.microserviceBlog.postService;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;

public class ServiceConfig {
	
	@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("me.kadary.microserviceBlog.apiGateway");
		}
	}

}
