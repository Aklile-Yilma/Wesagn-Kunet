package com.gov.wesagnkunet.admin.data.repositories;

import java.util.List;

import com.gov.wesagnkunet.admin.data.models.ContactMessage;

import org.springframework.data.repository.CrudRepository;


public interface ContactMessageRepository extends CrudRepository<ContactMessage, Long>{
	
	List<ContactMessage> findAllByOrderByDateDesc();

}
