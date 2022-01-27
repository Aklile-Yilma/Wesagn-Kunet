package com.gov.wesagnkunet.client.controllers.services.forms;



import java.sql.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.MarriageCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.controllers.services.forms.components.NameForm;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.models.MarriageCertificate.Spouse;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.format.annotation.DateTimeFormat;
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

	@NotBlank(message = "Date of marriage is required")
	@DateTimeFormat
	private Date marriageDate;

	private SpouseForm wife;

	private SpouseForm husband;
	
	@AssertTrue(message = "Invalid Date")
	public boolean isMarriageDateValid(){
		if(marriageDate == null)
			return true;
		return new Date(System.currentTimeMillis()).after(marriageDate);
	}

	@AssertTrue(message = "Invalid Date")
	public boolean isWifeDateOfBirthValid(){
		if(wife.getDateOfBirth() == null)
			return true;
		return new Date(System.currentTimeMillis()).after(wife.getDateOfBirth());
	}

	@AssertTrue(message= "Invalid Date")
	public boolean isHusbandDateOfBirthValid(){
		if(husband.getDateOfBirth() == null)
			return true;
		return new Date(System.currentTimeMillis()).after(wife.getDateOfBirth());
	}

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



		@NotBlank(message = "Nationality is required")
		@NotNull
		private Nationality nationality;

			@NotBlank(message = "Date of birth is required")
	        @DateTimeFormat
			private Date dateOfBirth;

		@NotBlank(message = "Photo is required")
		private MultipartFile photo;

		

		public Spouse toSpouse(FileStorageService storageService){
			return new Spouse(
				fullName.toName(),
				nationality.getName(),
				dateOfBirth,
				storageService.store(photo)
			);
		}

	}



}
