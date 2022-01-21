package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table( name = "client_certificatedetails")
public class CertificateDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	private List<Client> owners;

	@ManyToMany
	private List<Client> readAuthenticated;

	private Date issueDate;

	private Date applicationDate;

	@Enumerated(EnumType.STRING)
	private Type type;

	public CertificateDetails(Date issueDate, Date applicationDate, Type type){
		this.issueDate = issueDate;
		this.applicationDate = applicationDate;
		this.type = type;
	}

	public static enum Type{
		BIRTH, MARRIAGE, DEATH
	}

	
}
