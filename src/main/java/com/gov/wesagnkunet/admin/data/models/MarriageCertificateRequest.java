package com.gov.wesagnkunet.admin.data.models;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate;
import com.gov.wesagnkunet.client.data.models.CertificateDetails.Type;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate.Spouse;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.MarriageCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin_marriagecertificaterequest")
public class MarriageCertificateRequest implements CertificateRequest{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private CertificateRequestDetails certificateRequestDetails;

	@Embedded
	private Address marriageAddress;

	private Date marriageDate;


	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "fullName.firstName", column = @Column(name = "wife_firstname")),
		@AttributeOverride(name = "fullName.middleName", column = @Column(name = "wife_middlename")),
		@AttributeOverride(name = "fullName.lastName", column = @Column(name = "wife_lastname")),
		@AttributeOverride(name = "nationality", column = @Column(name = "wife_nationality")),
		@AttributeOverride(name = "dateOfBirth", column = @Column(name = "wife_dateofbirth")),
		@AttributeOverride(name = "photo", column = @Column(name = "wife_photo"))
	})
	private Spouse wife;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "fullName.firstName", column = @Column(name = "husband_firstname")),
		@AttributeOverride(name = "fullName.middleName", column = @Column(name = "husband_middlename")),
		@AttributeOverride(name = "fullName.lastName", column = @Column(name = "husband_lastname")),
		@AttributeOverride(name = "nationality", column = @Column(name = "husband_nationality")),
		@AttributeOverride(name = "dateOfBirth", column = @Column(name = "husband_dateofbirth")),
		@AttributeOverride(name = "photo", column = @Column(name = "husband_photo"))
	})
	private Spouse husband;


	public MarriageCertificateRequest(
		CertificateRequestDetails certificateRequestDetails,
		Address marriageAddress,
		Date marriageDate,
		Spouse wife,
		Spouse husband
	){
		this.certificateRequestDetails = certificateRequestDetails;
		this.marriageAddress = marriageAddress;
		this.marriageDate= marriageDate;
		this.wife = wife;
		this.husband = husband;
	}

	public MarriageCertificate toMarriageCertificate(CertificateDetailsRepository certificateDetailsRepository, MarriageCertificateRepository marriageCertificateRepository){
		MarriageCertificate certificate = new MarriageCertificate(
			certificateRequestDetails.toCertificateDetails(certificateDetailsRepository, Type.MARRIAGE),
			marriageAddress,
			marriageDate,
			wife,
			husband
		);

		marriageCertificateRepository.save(certificate);

		return certificate;

	}

}
