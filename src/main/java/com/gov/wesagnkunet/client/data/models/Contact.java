package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Contact {
	
	@Email(message = "Please provide valid email")
	@NotBlank(message = "email is required")
	private String email;

	@NumberFormat
	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

}
