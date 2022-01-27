package com.gov.wesagnkunet.client.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CollectionId;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {

	@NotNull(message = "Please select country")
	@ManyToOne
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

	// private String nationality;


	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name = "client_address_country")
	public static class Country {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(unique = true)
		private String name;

		public Country(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}



	@Data
	@NoArgsConstructor
	@Entity
	@Table(name = "client_address_nationality")
	public static class Nationality {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(unique = true)
		private String name;
		
		public Nationality(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}

}
