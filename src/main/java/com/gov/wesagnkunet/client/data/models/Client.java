package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gov.wesagnkunet.lib.auth.data.models.User;

import lombok.Data;


@Data
@Entity
@Table(name = "client_client")
public class Client {

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(unique = true, nullable = true)
	private User user;

	@Embedded
	private Name fullName;

	private String photo;

	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Embedded
	private Address address;

	@ManyToMany(mappedBy = "owners")
	private List<CertificateDetails> certificates;

	@Embedded
	private Contact contact;

	public static enum Sex{
		FEMALE, MALE
	}

	public static enum BloodType{
		A_PLUS, A_MINUS, AB_PLUS, AB_MINUS, B_PLUS, B_MINUS, O_PLUS, O_MINUS
	}

}
