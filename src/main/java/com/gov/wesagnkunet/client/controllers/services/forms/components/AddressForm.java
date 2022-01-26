package com.gov.wesagnkunet.client.controllers.services.forms.components;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;

import lombok.Data;

@Data
public class AddressForm {
	
	@NotNull
	private Country country;
	
	@NotNull
	private String city;
	
	@NotNull
	private String subCity;
	
	@Min(1)
	private Integer wereda;
	
	@Min(1)
	private Integer houseNumber;
	
	public Address toAddress(){
		return new Address(
				country,
			city,
			subCity,
			wereda,
			houseNumber
		);
	}

}
