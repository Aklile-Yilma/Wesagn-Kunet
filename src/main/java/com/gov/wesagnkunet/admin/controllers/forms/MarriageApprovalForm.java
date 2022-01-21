package com.gov.wesagnkunet.admin.controllers.forms;

import javax.persistence.Transient;

import com.gov.wesagnkunet.admin.data.models.MarriageCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.MarriageCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarriageApprovalForm {

	@Transient
	MarriageCertificateRepository marriageCertificateRepository;
	
	@Transient
	CertificateDetailsRepository certificateDetailsRepository;

	@Transient
	MarriageCertificateRequestRepository marriageCertificateRequestRepository;


	private MarriageCertificateRequest request;

	private Boolean approved;

	public MarriageApprovalForm(MarriageCertificateRepository marriageCertificateRepository, CertificateDetailsRepository certificateDetailsRepository, MarriageCertificateRequestRepository marriageCertificateRequestRepository){
		this.marriageCertificateRepository = marriageCertificateRepository;
		this.certificateDetailsRepository = certificateDetailsRepository;
		this.marriageCertificateRequestRepository = marriageCertificateRequestRepository;
	}

	private MarriageCertificate createCertificate(){
		MarriageCertificate certificate = request.toMarriageCertificate(certificateDetailsRepository, marriageCertificateRepository);
		request.getCertificateRequestDetails().setApproved(true);
		marriageCertificateRequestRepository.save(request);
		return certificate;
	}

	private void deleteRequest(){
		marriageCertificateRequestRepository.delete(request);
	}

	public void handle(){
		if(approved)
			createCertificate();
		else
			deleteRequest();
	}

	

}
