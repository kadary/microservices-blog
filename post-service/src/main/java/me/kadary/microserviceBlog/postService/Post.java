package me.kadary.microserviceBlog.postService;

import org.springframework.data.annotation.Id;

public class Post {
	
	@Id
	private String postId;
	
	private String userId;
	private String title;
	private String content;
	
	public Post() {
	
	}


	public Post(String userId, String title, String content) {
		this.setUserId(userId);
		this.setTitle(title);
		this.setContent(content);
	}
	
	public String getPostId() {
		return postId;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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
                "Post[postId=%s, userId='%s', title='%s', content='%s']",
                postId, userId, title, content);
	}

}
