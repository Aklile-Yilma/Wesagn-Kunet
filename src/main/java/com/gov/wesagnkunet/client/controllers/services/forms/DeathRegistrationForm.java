package com.gov.wesagnkunet.client.controllers.services.forms;



import java.util.Date;

import javax.persistence.Transient;

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

    private Nationality nationality;
    

    @Transient
    private FileStorageService storageService;

    @Transient
    private DeathCertificateRequestRepository deathCertificateRequestRepository;

    private AddressForm address;

    private Date dateOfBirth;
    private Date dateOfDeath;

    private Name name;
    private Title title;


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

