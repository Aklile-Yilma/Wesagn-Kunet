package com.gov.wesagnkunet.client.controllers.services.forms.components;

import com.gov.wesagnkunet.client.data.models.Name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameForm {

	private String firstName;

	private String middleName;

	private String lastName;
	
	public Name toName(){
		return new Name(firstName, middleName, lastName);
	}

}
