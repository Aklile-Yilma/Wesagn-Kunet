package com.gov.wesagnkunet.admin.data.models;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.DeathCertificate;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.models.CertificateDetails.Type;
import com.gov.wesagnkunet.client.data.models.DeathCertificate.Title;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin_deathcertificaterequest")
public class DeathCertificateRequest implements CertificateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private CertificateRequestDetails certificateRequestDetails;

    @Embedded
    private Address address;

    private Title title;

    private Date dateOfDeath;
    private Date dateOfBirth;

    private Name name;
   

    // @Override
    // public CertificateRequestDetails getCertificateRequestDetails() {
    //     // TODO Auto-generated method stub
    //     return null;
    // }
    public DeathCertificateRequest(
           CertificateRequestDetails certificateRequestDetails2,
           Name name,
            Address address,
            Date dateOfBirth,
            Date dateOfDeath,
            Title title
            
    ) {
        this.certificateRequestDetails = certificateRequestDetails2;
        this.address =address;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        
        this.title = title;
    }

    public DeathCertificate toDeathCertificate(CertificateDetailsRepository certificateDetailsRepository,
            DeathCertificateRepository deathCertificateRepository) {
        
                DeathCertificate certificate =new DeathCertificate(
                    certificateRequestDetails.toCertificateDetails(certificateDetailsRepository, Type.DEATH),
                     address,
                      dateOfBirth,
                        dateOfDeath,
                       title, 
                       
                       name

                );

                deathCertificateRepository.save(certificate);

                return certificate;
    }
}
