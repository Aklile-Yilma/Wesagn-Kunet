package com.gov.wesagnkunet.client.data.repositories;

import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.lib.auth.data.models.User;

import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, String>{
	
	public Client findByUser(User user);
}
