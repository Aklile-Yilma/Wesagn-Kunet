package com.gov.wesagnkunet.client.data.repositories;

import java.sql.Date;
import java.util.List;

import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.DeathCertificate;

import org.springframework.data.repository.CrudRepository;

public interface DeathCertificateRepository extends CrudRepository<DeathCertificate, String>{
	
	public Long countByCertificateDetailsIssueDateGreaterThan(Date issueDate);

	public List<DeathCertificate> findByCertificateDetailsOwnersContains(Client client);

}
