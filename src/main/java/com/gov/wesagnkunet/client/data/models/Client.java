package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
	private Long id;

	@ManyToOne
	private User user;

	private String firstName;

	private String middleName;

	private String lastName;

	private String photo;

	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Embedded
	private Address address;

	@Embedded
	private Contact contact;

	@Embedded
	private Contact emergencyContact;

	@ManyToMany(mappedBy = "client")
	private List<Certificate> certificates;

	public static enum Sex{
		FEMALE, MALE
	}

	public static enum BloodType{
		A_PLUS, A_MINUS, AB_PLUS, AB_MINUS, B_PLUS, B_MINUS, O_PLUS, O_MINUS
	}

}
