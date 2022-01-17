package com.gov.wesagnkunet.client.data.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "client_certificate")
public class CertificateDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@ManyToMany
	private List<Client> owners;

	@ManyToMany
	private List<Client> readAuthenticated;

	private Date issueDate;

	private Date applicationDate;


	@Enumerated(EnumType.STRING)
	private Type type;

	public static enum Type{
		BIRTH, MARRIAGE, DEATH
	}

	
}
