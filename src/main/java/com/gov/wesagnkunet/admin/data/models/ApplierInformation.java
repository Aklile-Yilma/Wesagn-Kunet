package com.gov.wesagnkunet.admin.data.models;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.gov.wesagnkunet.client.data.models.Name;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ApplierInformation {
	
	
	private Name fullName;

	private String email;

	private String phoneNumber;


}
