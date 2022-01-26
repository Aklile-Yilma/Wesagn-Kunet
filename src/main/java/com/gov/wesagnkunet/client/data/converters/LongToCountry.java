package com.gov.wesagnkunet.client.data.converters;

import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;

import org.springframework.core.convert.converter.Converter;


public class LongToCountry implements Converter<Long, Country>{

	private CountryRepository countryRepository;

	public LongToCountry(CountryRepository countryRepository){
		this.countryRepository = countryRepository;
	}

	@Override
	public Country convert(Long source) {
		return countryRepository.findById(source).get();
	}
	
}
