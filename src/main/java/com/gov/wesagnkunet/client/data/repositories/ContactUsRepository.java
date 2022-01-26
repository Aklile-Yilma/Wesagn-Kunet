package com.gov.wesagnkunet.client.data.repositories;
import com.gov.wesagnkunet.client.controllers.services.forms.ContactUsForm;
import org.springframework.data.repository.CrudRepository;

public interface ContactUsRepository extends CrudRepository<ContactUsForm,String>{
    
}
