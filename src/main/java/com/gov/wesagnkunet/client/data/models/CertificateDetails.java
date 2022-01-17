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
	private String id;

	@ManyToMany
	private List<Client> owners;

	@ManyToMany
	private List<Client> readAuthenticated;

	private Date issueDate;

	private Date applicationDate;

	@Column(unique = true)
	private String token;


	@Enumerated(EnumType.STRING)
	private Type type;

	public static enum Type{
		BIRTH, MARRIAGE, DEATH
	}

	
}
