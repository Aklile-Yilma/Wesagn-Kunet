package com.gov.wesagnkunet.lib.auth;

import com.gov.wesagnkunet.lib.auth.data.models.User;
import com.gov.wesagnkunet.lib.auth.data.models.User.Role;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;
import com.gov.wesagnkunet.lib.auth.exceptions.UserExistsException;

import org.springframework.security.crypto.password.PasswordEncoder;


public class UserManager {

	private UserRepository repository;
	private PasswordEncoder encoder;

	public UserManager(UserRepository repository, PasswordEncoder encoder){
		this.repository = repository;
		this.encoder = encoder;
	}

	public User createUser(String username, String password, Role role) throws UserExistsException{

		if(userExists(username))
			throw new UserExistsException(String.format("User with username %s already exists.", username));
		
		User user = new User(username, encoder.encode(password), role);
		repository.save(user);

		return user;
		
	}

	public boolean userExists(String username){
		return repository.existsById(username);
	}
	
}
