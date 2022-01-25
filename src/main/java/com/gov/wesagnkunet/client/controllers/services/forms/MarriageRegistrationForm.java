package com.gov.wesagnkunet.client.controllers.services.forms;



import java.sql.Date;

import javax.persistence.Transient;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.MarriageCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.controllers.services.forms.components.NameForm;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate.Spouse;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarriageRegistrationForm {
	
	@Transient
	private Client client;

	@Transient
	private FileStorageService storageService;

	@Transient
	private MarriageCertificateRequestRepository marriageCertificateRequestRepository;

	private AddressForm marriageAddress;

	private Date marriageDate;

	private SpouseForm wife;

	private SpouseForm husband;
	
	public MarriageRegistrationForm(Client client, FileStorageService storageService, MarriageCertificateRequestRepository marriageCertificateRequestRepository){
		this.client = client;
		this.storageService = storageService;
		this.marriageCertificateRequestRepository = marriageCertificateRequestRepository;
	}

	public MarriageCertificateRequest createCertificateRequest(){
	
		MarriageCertificateRequest request = new MarriageCertificateRequest(
			new CertificateRequestDetails(client),
			marriageAddress.toAddress(),
			marriageDate,
			wife.toSpouse(storageService),
			husband.toSpouse(storageService)
		);

		marriageCertificateRequestRepository.save(request);

		return request;

	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class SpouseForm{

		private NameForm fullName;

		private String nationality ="Ethiopian";

		private Date dateOfBirth;

		private MultipartFile photo;

		public Spouse toSpouse(FileStorageService storageService){
			return new Spouse(
				fullName.toName(),
				nationality,
				dateOfBirth,
				storageService.store(photo)
			);
		}

<<<<<<< HEAD
=======
		// @Override
		// public String toString(){
		// 	return this.nationality;
		// }
>>>>>>> 415c3dfb19dd246e1991cd0b713cdd4a74f4d32c
	}



}
