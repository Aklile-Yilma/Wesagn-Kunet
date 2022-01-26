package com.gov.wesagnkunet.admin.data.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "admin_contactmessage")
public class ContactMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	
	private String lastName;

	private String phoneNumber;

	private String email;

	@Column(length = 512)
	private String message;

	private Date date;
	
	
	public ContactMessage(
		String firstName,
		String lastName,
		String phoneNumber,
		String email,
		String message
	){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.message = message;
		this.date = new Date(System.currentTimeMillis());
	}

}
