package com.gov.wesagnkunet.admin.controllers.forms;
import java.security.cert.CertificateFactory;

import javax.persistence.Transient;

import com.gov.wesagnkunet.admin.data.models.BirthCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.models.BirthCertificate;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BirthApprovalForm {

    @Transient
    BirthCertificateRepository birthCertificateRepository;

    @Transient
    CertificateDetailsRepository certificateDetailsRepository;

    @Transient
    BirthCertificateRequestRepository birthCertificateRequestRepository;

    private BirthCertificateRequest request;

    private Boolean approved;

    public BirthApprovalForm(BirthCertificateRepository birthCertificateRepositoryIn, CertificateDetailsRepository certificateDetailsRepositoryIn, BirthCertificateRequestRepository birthCertificateRequestRepositoryIn){
        this.birthCertificateRepository=birthCertificateRepositoryIn;
        this.certificateDetailsRepository=certificateDetailsRepositoryIn;
        this.birthCertificateRequestRepository= birthCertificateRequestRepositoryIn;


    }

    private BirthCertificate createCertificate(){
        BirthCertificate certificate = request.toBirthCertificate(certificateDetailsRepository, birthCertificateRepository);
        request.getCertificateRequestDetails().setApproved(true);
        birthCertificateRequestRepository.save(request);

        return certificate;
    }

    private void deleteRequest(){
        birthCertificateRequestRepository.delete(request);
    }

    public void handle(){
        if(approved){
            createCertificate();
        }else{
        deleteRequest();    
        }    
        
    }

    

    
}
