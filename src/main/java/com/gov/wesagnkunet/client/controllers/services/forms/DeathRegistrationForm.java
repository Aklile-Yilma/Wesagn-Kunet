package com.gov.wesagnkunet.client.controllers.services.forms;



import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.DeathCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.models.DeathCertificate.Title;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeathRegistrationForm {
    @Transient
    private Client client;

	@NotNull(message = "Nationality is required")
    private Nationality nationality;
    
    @Transient
    private FileStorageService storageService;

    @Transient
    private DeathCertificateRequestRepository deathCertificateRequestRepository;

    private AddressForm address;

    private Date dateOfBirth;
    private Date dateOfDeath;

    private Name name;

	@NotNull
	@Enumerated(EnumType.STRING)
    private Title title;

	@AssertTrue(message = "Invalid Date")
	public boolean isBirthDateValid(){
		if(dateOfBirth == null)
			return true;
		return new Date(System.currentTimeMillis()).after(dateOfBirth);
	}

	@AssertTrue(message = "Invalid Date")
	public boolean isDeathDateValid(){
		if(dateOfBirth == null)
			return true;
		return new Date(System.currentTimeMillis()).after(dateOfBirth);
	}

    public DeathRegistrationForm(Client client, FileStorageService storageService, 
            DeathCertificateRequestRepository deathCertificateRequestRepository) {
        this.client = client;
        this.storageService = storageService;
        this.deathCertificateRequestRepository = deathCertificateRequestRepository;
    }

    public DeathCertificateRequest   createCertificateRequest() {
        DeathCertificateRequest request = new DeathCertificateRequest(
            new CertificateRequestDetails(client),
                         name,
                         address.toAddress(),
                        dateOfBirth,
                         dateOfDeath,
                          title
            
        );
        deathCertificateRequestRepository.save(request);

        return request;
    }
    
}

