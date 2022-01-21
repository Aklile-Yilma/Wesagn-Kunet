package com.gov.wesagnkunet.admin.data.repositories;

import com.gov.wesagnkunet.admin.data.models.Admin;
import com.gov.wesagnkunet.lib.auth.data.models.User;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	public Admin findByUser(User user);

}
