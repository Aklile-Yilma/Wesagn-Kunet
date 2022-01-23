package com.gov.wesagnkunet.admin.data.repositories;

import com.gov.wesagnkunet.admin.data.models.DeathCertificateRequest;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeathCertificateRequestRepository extends CrudRepository<DeathCertificateRequest, Long> {
    public List<DeathCertificateRequest> findByCertificateRequestDetailsApproved(Boolean approved);
    
}
