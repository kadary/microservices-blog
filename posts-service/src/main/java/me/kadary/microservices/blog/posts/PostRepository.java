package me.kadary.microservices.blog.posts;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
	
	public Post findByTitle(String title);

	public List<Post> findByUserId(String userId);
	
	public List<Post> findByPostId(String postId);
}
