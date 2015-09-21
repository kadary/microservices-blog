package me.kadary.microserviceBlog.userService;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUserId(String userID);
	public User findByUsername(String username);
	
}
