package com.gov.wesagnkunet.admin.data.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.gov.wesagnkunet.client.data.models.Name;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ApplierInformation {
	
	
	
	private Name fullName;
	
	@Email(message = "Please provide valid email format")
	@NotBlank(message = "Please provide your email")
	private String email;

	@Size(min = 10, max = 10)
	@NotBlank(message="Please enter your phone number")
	private String phoneNumber;


}
