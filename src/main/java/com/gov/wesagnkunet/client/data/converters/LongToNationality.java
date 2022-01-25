package com.gov.wesagnkunet.client.data.converters;

import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.repositories.NationalityRepository;

import org.springframework.core.convert.converter.Converter;

public class LongToNationality implements Converter<Long, Nationality> {
    private NationalityRepository nationalityRepository;

    public LongToNationality(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    public Nationality convert(Long source) {
        return nationalityRepository.findById(source).get();
    }
    
}
