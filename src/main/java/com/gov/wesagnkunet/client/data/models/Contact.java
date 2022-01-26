package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Contact {
	
	private String email;
	private String phoneNumber;

}
