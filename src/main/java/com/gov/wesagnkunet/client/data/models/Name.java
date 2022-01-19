package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Name {

	private String firstName;

	private String middleName;

	private String lastName;

	@Override
	public String toString(){
		return String.format("%s %s", firstName, middleName);
	}
	
}
