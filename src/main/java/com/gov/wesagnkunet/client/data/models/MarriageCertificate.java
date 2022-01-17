package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
@Table(name = "client_marriagecertificate")
public class MarriageCertificate implements Certificate{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@JoinColumn(nullable = false)
	@ManyToOne
	private CertificateDetails certificateDetails;

	@Embedded
	private Address marriageAddress;

	private Date marriageDate;


	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "fullName.firstName", column = @Column(name = "wife_firstname")),
		@AttributeOverride(name = "fullName.middleName", column = @Column(name = "wife_middlename")),
		@AttributeOverride(name = "fullName.lastName", column = @Column(name = "wife_lastname")),
		@AttributeOverride(name = "nationality", column = @Column(name = "wife_nationality")),
		@AttributeOverride(name = "dateOfBirth", column = @Column(name = "wife_dateofbirth")),
		@AttributeOverride(name = "photo", column = @Column(name = "wife_photo"))
	})
	private Spouse wife;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "fullName.firstName", column = @Column(name = "husband_firstname")),
		@AttributeOverride(name = "fullName.middleName", column = @Column(name = "husband_middlename")),
		@AttributeOverride(name = "fullName.lastName", column = @Column(name = "husband_lastname")),
		@AttributeOverride(name = "nationality", column = @Column(name = "husband_nationality")),
		@AttributeOverride(name = "dateOfBirth", column = @Column(name = "husband_dateofbirth")),
		@AttributeOverride(name = "photo", column = @Column(name = "husband_photo"))
	})
	private Spouse husband;

	
	@Data
	@Embeddable
	private class Spouse{

		@Embedded
		private Name fullName;

		private String nationality;

		private Date dateOfBirth;

		private String photo;

	}
}
