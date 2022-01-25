package com.gov.wesagnkunet.client.data.repositories;

import java.sql.Date;

import com.gov.wesagnkunet.client.data.models.MarriageCertificate;

import org.springframework.data.repository.CrudRepository;

public interface MarriageCertificateRepository extends CrudRepository<MarriageCertificate, String>{

	public Long countByCertificateDetailsIssueDateGreaterThan(Date issueDate);

}
