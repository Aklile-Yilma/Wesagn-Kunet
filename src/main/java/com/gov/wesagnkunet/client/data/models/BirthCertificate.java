package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
@Table(name = "client_birthcertificate")
public class BirthCertificate implements Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(nullable = false, unique = true)
	@ManyToOne
	private CertificateDetails certificateDetails;

	@Embedded
	private ChildInformation childInformation;

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

	@Data
	@Embeddable
	public static class ChildInformation{

		@Embedded
		private Name fullName;

		private Date dateOfBirth;

		@Enumerated(EnumType.STRING)
		private Client.Sex sex;

		private String photo;

		@Embedded
		private Address birthAddress;

		private String nationality;

	}

	@Data
	@Embeddable
	public static class ParentInformation{

		@Embedded
		private Name fullName;

		private String nationality;

	}
}
