package me.kadary.microserviceBlog.userService;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String userId;
	
	private String username;
	private String password;
	
	public User() {
	
	}


	public User(String username, String password) {
		this.setUserId(username);
		this.setPassword(password);
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return String.format(
                "User[userId=%s, username='%s']",
                userId, username);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
