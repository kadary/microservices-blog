package me.kadary.microserviceBlog.postService;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableOAuth2Resource
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/posts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getAllPosts() {
		return this.repository.findAll();
	}

	@RequestMapping(method=RequestMethod.GET, value="api/post/{postId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPost(@PathVariable String postId) {
		return this.repository.findByPostId(postId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/posts")
	@Produces(MediaType.APPLICATION_JSON)
	public Post addPost(@RequestBody Post post) {
		this.repository.save(post);
		return post;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/posts/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getUserPosts(@PathVariable String userId) {
		return this.repository.findByUserId(userId);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PostService.class, args);
	}
}
