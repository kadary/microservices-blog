package me.kadary.microserviceBlog.apiGateway;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import me.kadary.microserviceBlog.postService.Post;
import me.kadary.microserviceBlog.postService.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;


@Named
@Path("/api")
public class PostAPI {
	
	@Autowired
	private PostRepository repository;
	
	@GET
	@Path("posts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getAllPosts() {
		return this.repository.findAll();
	}
	
	@GET
	@Path("posts/user")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getUserPosts(@QueryParam("userId") String userId) {
		return this.repository.findByUserId(userId);
	}
	
	@POST
	@Path("posts")
	@Produces(MediaType.APPLICATION_JSON)
	public Post addPost(@RequestBody Post post) {
		this.repository.save(post);
		return post;
	}	
}
