package com.gov.wesagnkunet.client.controllers.services.forms;
import java.sql.Date;

import javax.persistence.Transient;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.BirthCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.controllers.services.forms.components.NameForm;
import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ChildInformation;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ParentInformation;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.apache.groovy.parser.antlr4.GroovyParser.SuperPrmrAltContext;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BirthRegistrationForm {

    @Transient //specifies that an object is not to be persisted
    private Client client;

    @Transient 
    private FileStorageService storageService;

    @Transient
    private BirthCertificateRequestRepository birthCertificateRequestRepository;

    private AddressForm birthAddress;

    private Date birthDate;

    private ChildForm child;

    private ParentForm father;
    private ParentForm mother;

    public BirthRegistrationForm(Client clientIn, FileStorageService fileStorageServiceIn, BirthCertificateRequestRepository birthCertificateRequestRepositoryIn)
    {
        
        this.client= clientIn;
        this.storageService=fileStorageServiceIn;
        this.birthCertificateRequestRepository=birthCertificateRequestRepositoryIn;

    }

    public BirthCertificateRequest createCertificateRequest(){

        BirthCertificateRequest request= new BirthCertificateRequest(
            new CertificateRequestDetails(client),
            child.toChild(storageService),
            father.toParent(storageService),
            mother.toParent(storageService)
        );

        birthCertificateRequestRepository.save(request);

        return request;
        
    }

    
    public static class ChildForm{

        private NameForm fullName;
        private String nationality= "Ethiopian";

        private Date dateOfBirth;
        private Client.Sex sex;
        private MultipartFile photo;
        private Address birthAddress;


        public ChildInformation toChild(FileStorageService storageService){
            return new ChildInformation(
                fullName.toName(),
                dateOfBirth,
                sex,
                storageService.store(photo),
                birthAddress,
                nationality
            );
        }

            @Override
            public String toString(){
                return this.nationality;
            }
        }


        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class ParentForm{
            private NameForm fullName;
            private String nationality;

            public ParentInformation toParent(FileStorageService storageService){
                return new ParentInformation(
                    fullName.toName(),
                    nationality
                );

            }

        }
    }

     


