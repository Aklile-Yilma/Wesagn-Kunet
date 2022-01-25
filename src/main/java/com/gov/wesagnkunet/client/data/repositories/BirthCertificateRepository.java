package com.gov.wesagnkunet.client.data.repositories;

import java.sql.Date;

import com.gov.wesagnkunet.client.data.models.BirthCertificate;
import com.gov.wesagnkunet.client.data.models.CertificateDetails;

import org.springframework.data.repository.CrudRepository;

public interface BirthCertificateRepository extends CrudRepository<BirthCertificate, String>{

	public BirthCertificate findByCertificateDetails(CertificateDetails details);

	public Long countByCertificateDetailsIssueDateGreaterThan(Date issueDate);
	
}
