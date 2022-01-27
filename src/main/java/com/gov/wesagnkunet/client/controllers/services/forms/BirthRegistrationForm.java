package com.gov.wesagnkunet.client.controllers.services.forms;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.models.BirthCertificateRequest;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.controllers.services.forms.components.NameForm;
import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ChildInformation;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ParentInformation;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.apache.groovy.parser.antlr4.GroovyParser.SuperPrmrAltContext;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.format.annotation.DateTimeFormat;
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
            mother.toParent(),
            father.toParent()
        );

        birthCertificateRequestRepository.save(request);

        return request;
        
    }

    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildForm{

		private NameForm fullName;

        @NotBlank(message = "Nationality is required")
        private String nationality;

        @NotBlank(message = "Date of birth is required")
        @DateTimeFormat
        private Date dateOfBirth;
        
        @NotNull
        private Client.Sex sex;
        private MultipartFile photo;
        private Address birthAddress;

		
		

		

		


		@AssertTrue(message = "Invalid Date")
		public boolean isBirthDateValid(){
			if(dateOfBirth == null)
				return true;
			return new Date(System.currentTimeMillis()).after(dateOfBirth);
		}

        public ChildInformation toChild(FileStorageService storageService){
            return new ChildInformation(
                fullName.toName(),
                dateOfBirth,
                sex,
                storageService.store(photo),
                birthAddress,
                nationality.toString()
            );
        }
    }


        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class ParentForm{
            private NameForm fullName;
        
            @NotBlank(message = "Nationality is required")
            private String nationality;

		public ParentInformation toParent(){
			return new ParentInformation(
				fullName.toName(),
				nationality.toString()
			);

		}

	}
}

     


