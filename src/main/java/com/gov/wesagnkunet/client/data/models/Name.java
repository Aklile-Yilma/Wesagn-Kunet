package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Name {

	@NotBlank(message = "Please provide first name")
	private String firstName;

	@NotBlank(message = "Please provide middle name")
	private String middleName;

	@NotBlank(message = "Please provide last name")
	private String lastName;

	@Override
	public String toString(){
		return String.format("%s %s", firstName, middleName);
	}
	
}
