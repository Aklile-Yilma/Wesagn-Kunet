package com.gov.wesagnkunet.lib.auth.data.repositories;

import com.gov.wesagnkunet.lib.auth.data.models.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{

	public User findByUsername(String username);

}