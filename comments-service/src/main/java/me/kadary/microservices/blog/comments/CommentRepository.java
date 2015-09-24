package me.kadary.microservices.blog.comments;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
	
	public List<Comment> findByUserId(String userId);
	
	public List<Comment> findByPostId(String postId);
}
