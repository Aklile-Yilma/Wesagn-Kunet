package com.gov.wesagnkunet.client.data.repositories;

import java.util.List;

import com.gov.wesagnkunet.client.data.models.Address.Country;

import org.springframework.data.repository.CrudRepository;


public interface CountryRepository extends CrudRepository<Country, Long>{
	
	public List<Country> findAll();

}
