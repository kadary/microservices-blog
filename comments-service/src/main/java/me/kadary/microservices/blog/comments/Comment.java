package me.kadary.microservices.blog.comments;

import org.springframework.data.annotation.Id;

public class Comment {
	
	@Id
	private String commentId;
	
	private String userId;
	private String postId;
	private String content;
	
	public Comment() {
	
	}


	public Comment(String userId, String postId, String content) {
		this.setUserId(userId);
		this.setPostId(postId);
		this.setContent(content);
	}
	
	public String getCommentId() {
		return commentId;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPostId() {
		return postId;
	}


	public void setPostId(String postId) {
		this.postId = postId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return String.format(
                "Comment[commentId=%s, userId='%s', postId='%s', content='%s']",
                commentId, userId, postId, content);
	}

}
