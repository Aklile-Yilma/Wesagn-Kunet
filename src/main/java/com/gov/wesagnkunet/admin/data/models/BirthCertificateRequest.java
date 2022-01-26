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

import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.BirthCertificate;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ChildInformation;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ParentInformation;
import com.gov.wesagnkunet.client.data.models.CertificateDetails.Type;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="admin_birthCertificateRequest")
public class BirthCertificateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private CertificateRequestDetails certificateRequestDetails;


    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "fullName.firstName", column = @Column(name = "child_firstname")),
        @AttributeOverride(name= "fullName.middleName", column = @Column(name= "child_middlename")),
        @AttributeOverride(name= "fullName.lastName", column = @Column(name= "child_lastname")),
        @AttributeOverride(name="nationality", column = @Column(name="child_nationality")),
        @AttributeOverride(name="dateOfBirth", column= @Column(name="child_dateOfBirth")),
        @AttributeOverride(name="photo", column= @Column(name="child_photo"))

     } )
     private ChildInformation child;

     @Embedded
     @AttributeOverrides({
         @AttributeOverride(name = "fullName.firstName", column = @Column(name = "mother_firstname")),
         @AttributeOverride(name = "fullName.middleName", column = @Column(name = "mother_middlename")),
         @AttributeOverride(name = "fullName.lastName", column = @Column(name = "mother_lastname")),
         @AttributeOverride(name = "nationality", column = @Column(name = "mother_nationality"))
     })
     private ParentInformation motherInformation;
 
     @Embedded
     @AttributeOverrides({
         @AttributeOverride(name = "fullName.firstName", column = @Column(name = "father_firstname")),
         @AttributeOverride(name = "fullName.middleName", column = @Column(name = "father_middlename")),
         @AttributeOverride(name = "fullName.lastName", column = @Column(name = "father_lastname")),
         @AttributeOverride(name = "nationality", column = @Column(name = "father_nationality"))
     })
     private ParentInformation fatherInformation;

     public BirthCertificateRequest(
         CertificateRequestDetails certificateRequestDetailsIn,
         ChildInformation childIn,
         ParentInformation motherInformation,
         ParentInformation fatherInformation
     ){
         this.certificateRequestDetails=certificateRequestDetailsIn;
<<<<<<< HEAD
         this.child=childIn;
		 this.fatherInformation = fatherInformation;
		 this.motherInformation = motherInformation;
=======
         this.child = childIn;
         this.motherInformation = motherInformation;
         this.fatherInformation = fatherInformation;
>>>>>>> 4f536ec521014d691026ba0436f8a13c91a1a420
     }

     public BirthCertificate toBirthCertificate(CertificateDetailsRepository certificateDetailsRepositoryIn, BirthCertificateRepository birthCertificateRepositoryIn){
         BirthCertificate certificate= new BirthCertificate(
             certificateRequestDetails.toCertificateDetails(certificateDetailsRepositoryIn, Type.BIRTH),
<<<<<<< HEAD
             child,
			 motherInformation,
			 fatherInformation
=======
             child, motherInformation, fatherInformation
>>>>>>> 4f536ec521014d691026ba0436f8a13c91a1a420
         );
         birthCertificateRepositoryIn.save(certificate);

         return certificate;

     }

    
    
}
