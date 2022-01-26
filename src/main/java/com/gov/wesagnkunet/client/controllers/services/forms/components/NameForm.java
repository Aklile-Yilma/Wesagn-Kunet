package com.gov.wesagnkunet.client.controllers.services.forms.components;

import javax.validation.constraints.NotBlank;

import com.gov.wesagnkunet.client.data.models.Name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameForm {

	@NotBlank
	private String firstName;

	@NotBlank
	private String middleName;

	@NotBlank
	private String lastName;
	
	public Name toName(){
		return new Name(firstName, middleName, lastName);
	}

}
