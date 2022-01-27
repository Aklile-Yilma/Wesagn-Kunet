package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gov.wesagnkunet.client.data.models.Address.Nationality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "client_deathcertificate")
public class DeathCertificate implements Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private CertificateDetails certificateDetails;

	@Embedded
	private Name name;

	@Enumerated(EnumType.STRING)
	private Title title;

	private Date dateOfBirth;

	@ManyToOne
	private Nationality nationality;

	@Embedded
	private Address address;

	private Date dateOfDeath;

	public static enum Title {
		Mr, Mrs, Ms, Dr
	}

	public DeathCertificate(
		 CertificateDetails certificateDetails,
		 Address address,
		 Date dateOfBirth,
		 Date dateOfDeath,
		 
		Title title,
		 Name name
	) {
		 this.certificateDetails=certificateDetails;
		 this.address=address;
		 this.dateOfBirth=dateOfBirth;
		 this.dateOfDeath=dateOfDeath;
			this.nationality = nationality;
			this.title = title;
			this.name = name;
	 }

    public DeathCertificate(CertificateDetails certificateDetails2, Address address2, java.util.Date dateOfBirth2,
            java.util.Date dateOfDeath2, Title title, String nationality2, Name name2) {
    }	
	
}
