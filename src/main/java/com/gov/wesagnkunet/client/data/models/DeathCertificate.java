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
	private String id;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private CertificateDetails certificateDetails;

	@Embedded
	private Name name;

	@Enumerated(EnumType.STRING)
	private Title title;

	private Date dateOfBirth;

	private String nationality;

	@Embedded
	private Address address;

	private Date dateOfDeath;

	public static enum Title{
		Mr, Mrs, Ms, Dr
	}	
	
}
