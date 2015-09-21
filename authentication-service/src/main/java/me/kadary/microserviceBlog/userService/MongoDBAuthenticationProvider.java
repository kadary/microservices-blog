package me.kadary.microserviceBlog.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


public class MongoDBAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private UserRepository repository;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		UserDetails loadedUser;
        try {
        	loadedUser = (UserDetails) new User(this.repository.findByUsername(username).getUserId(), this.repository.findByUsername(username).getPassword());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
	}
}
