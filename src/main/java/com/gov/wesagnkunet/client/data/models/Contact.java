package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

import lombok.Data;


@Data
@Embeddable
public class Contact {
	
	private String email;
	private String phoneNumber;

}
