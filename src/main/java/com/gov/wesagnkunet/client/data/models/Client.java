package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.gov.wesagnkunet.lib.auth.data.models.User;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "client_client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(unique = true, nullable = true)
	private User user;

	@Embedded
	private Name fullName;

	@NotBlank(message = "Photo is required")
	private String photo;

	@DateTimeFormat
	@NotBlank(message = "Date is Required")
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


	public Client(User user, Name name, String photo, Date dateOfBirth, BloodType bloodType, Sex sex, Address address, Contact contact){
		this.user = user;
		this.fullName = name;
		this.photo = photo;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.sex = sex;
		this.address = address;
		this.contact = contact;
	}

	public static enum Sex{
		FEMALE, MALE
	}

	public static enum BloodType{
		A_PLUS, A_MINUS, AB_PLUS, AB_MINUS, B_PLUS, B_MINUS, O_PLUS, O_MINUS
	}

}
