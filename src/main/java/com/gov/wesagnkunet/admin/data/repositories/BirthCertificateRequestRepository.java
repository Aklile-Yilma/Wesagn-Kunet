package com.gov.wesagnkunet.admin.data.repositories;

import java.util.List;

import com.gov.wesagnkunet.admin.data.models.BirthCertificateRequest;

import org.springframework.data.repository.CrudRepository;

public interface BirthCertificateRequestRepository extends CrudRepository <BirthCertificateRequest, Long>{

    public List<BirthCertificateRequest> findByCertificateRequestDetailsApproved(Boolean approved);
    
}
