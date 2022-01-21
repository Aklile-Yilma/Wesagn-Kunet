package com.gov.wesagnkunet.admin.data.repositories;

import java.util.List;

import com.gov.wesagnkunet.admin.data.models.MarriageCertificateRequest;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate;

import org.springframework.data.repository.CrudRepository;

public interface MarriageCertificateRequestRepository extends CrudRepository<MarriageCertificateRequest, Long>{
	
	public List<MarriageCertificateRequest> findByCertificateRequestDetailsApproved(Boolean approved);

}
