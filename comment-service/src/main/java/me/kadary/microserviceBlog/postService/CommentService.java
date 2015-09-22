package me.kadary.microserviceBlog.postService;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
public class CommentService {
	
	@Autowired
	private CommentRepository repository;
	
	@RequestMapping(method=RequestMethod.GET, value="api/comments/{postId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getPost(@PathVariable String postId) {
		return this.repository.findByPostId(postId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment addPost(@RequestBody Comment comment) {
		this.repository.save(comment);
		return comment;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getUserComments(@QueryParam("userId") String userId) {
		return this.repository.findByUserId(userId);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CommentService.class, args);
	}
}
