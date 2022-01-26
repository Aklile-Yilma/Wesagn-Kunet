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

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsForm {

	@NotBlank(message = "Please provide first name")
    private String firstName;

	@NotBlank(message = "Please provide second name")
    private String lastName;

	@NotBlank(message = "Please provide phone number")
	@NumberFormat
	private String phoneNumber;
    
	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email")
    private String email;

	@NotBlank(message = "Please write some message")
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
