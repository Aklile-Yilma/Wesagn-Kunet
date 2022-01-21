package com.gov.wesagnkunet.admin.data.models;

import javax.persistence.Embeddable;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

import com.gov.wesagnkunet.client.data.models.CertificateDetails;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.CertificateDetails.Type;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class CertificateRequestDetails {

	@ManyToOne
	private Client client;

	private Date applicationDate;

	@Column(columnDefinition = "boolean default false")
	private boolean approved;

	public CertificateRequestDetails(Client client){
		this.client = client;
		this.approved = false;
		this.applicationDate = new Date(System.currentTimeMillis());
	}

	public CertificateDetails toCertificateDetails(CertificateDetailsRepository repository, Type type){
		CertificateDetails details = new CertificateDetails(
			new Date(System.currentTimeMillis()),
			applicationDate,
			type
		);
		details.setOwners(Arrays.asList(client));
		repository.save(details);
		return details;
	}

}
