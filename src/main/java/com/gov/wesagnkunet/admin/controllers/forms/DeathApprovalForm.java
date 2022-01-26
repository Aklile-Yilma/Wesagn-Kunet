package com.gov.wesagnkunet.admin.controllers.forms;

import javax.persistence.Transient;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.DeathCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.models.DeathCertificate;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeathApprovalForm {

    @Transient
    DeathCertificateRepository deathCertificateRepository;

    @Transient
    CertificateDetailsRepository certificateDetailsRepository;

    @Transient
    DeathCertificateRequestRepository deathCertificateRequestRepository;

    private DeathCertificateRequest request;

    private Boolean approved;

    public DeathApprovalForm(DeathCertificateRepository deathCertificateRepository,
            CertificateDetailsRepository certificateDetailsRepository,
            DeathCertificateRequestRepository deathCertificateRequestRepository) {
        this.deathCertificateRepository = deathCertificateRepository;
        this.certificateDetailsRepository = certificateDetailsRepository;
        this.deathCertificateRequestRepository = deathCertificateRequestRepository;
    }

    private DeathCertificate createCertificate() {
        DeathCertificate certificate = request.toDeathCertificate(certificateDetailsRepository,
                deathCertificateRepository);
        request.getCertificateRequestDetails().setApproved(true);
        deathCertificateRequestRepository.save(request);

        return certificate;
    }
    
    private void deleteRequest() {
        deathCertificateRequestRepository.delete(request);
    }

    public void handle() {
        if (approved) {
            createCertificate();
        } else {
            deleteRequest();
        }
    }
    
}
