package com.gov.wesagnkunet.client.controllers.core.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.gov.wesagnkunet.admin.data.models.ContactMessage;
import com.gov.wesagnkunet.admin.data.repositories.ContactMessageRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsForm {

	@NotBlank
    private String firstName;

	@NotBlank
    private String lastName;

	@NotBlank
	private String phoneNumber;
    
	@NotBlank
	@Email
    private String email;

	@NotBlank
	private String message;


	public void createContactMessage(
		ContactMessageRepository contactMessageRepository
	){

		contactMessageRepository.save(
			new ContactMessage(
				firstName,
				lastName,
				phoneNumber,
				email,
				message
			)
		);

	}
    

    
}
