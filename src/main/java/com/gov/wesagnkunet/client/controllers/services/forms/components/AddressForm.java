package com.gov.wesagnkunet.client.controllers.services.forms.components;


import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;

import lombok.Data;

@Data
public class AddressForm {
	
	private Country country;
	private Nationality nationality;
	
	private String city;
	
	private String subCity;
	
	private Integer wereda;
	
	private Integer houseNumber;
	
	public Address toAddress(){
		return new Address(
				country,
			nationality,
			city,
			subCity,
			wereda,
			houseNumber
		);
	}

}
