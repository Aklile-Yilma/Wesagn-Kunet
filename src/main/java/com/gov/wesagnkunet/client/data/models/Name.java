package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;

import lombok.Data;


@Data
@Embeddable
public class Name {

	private String firstName;

	private String middleName;

	private String lastName;
	
}
