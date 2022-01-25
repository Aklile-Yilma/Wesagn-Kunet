package com.gov.wesagnkunet.client.data.repositories;

import java.sql.Date;

import com.gov.wesagnkunet.client.data.models.DeathCertificate;

import org.springframework.data.repository.CrudRepository;

public interface DeathCertificateRepository extends CrudRepository<DeathCertificate, String>{
	
	public Long countByCertificateDetailsIssueDateGreaterThan(Date issueDate);

}
