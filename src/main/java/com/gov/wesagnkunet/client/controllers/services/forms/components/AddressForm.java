package com.gov.wesagnkunet.client.controllers.services.forms.components;


import javax.validation.constraints.NotBlank;

import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class AddressForm {

	@NotBlank(message = "Please select Country")
	private Country country;
	
	@NotBlank(message = "City is required")
	private String city;
	
	@NotBlank(message = "Subcity is required")
	private String subCity;
	
	@NotBlank(message = "Woreda is required")
	@NumberFormat
	private Integer wereda;
	
	@NotBlank(message = "House number is required")
	@NumberFormat
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
