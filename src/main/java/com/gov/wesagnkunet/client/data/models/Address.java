package com.gov.wesagnkunet.client.data.models;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {
	
	private String country;
	private String city;
	private String subCity;
	private Integer wereda;
	private Integer houseNumber;

}
