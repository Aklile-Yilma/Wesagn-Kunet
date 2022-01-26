package com.gov.wesagnkunet.client.controllers.core;

import javax.validation.Valid;

import java.security.Principal;

import com.gov.wesagnkunet.admin.data.repositories.ContactMessageRepository;
import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.core.forms.ContactUsForm;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactUsController  extends ClientController{

    @Autowired
	private ContactMessageRepository contactMessageRepository;


    @GetMapping("/contact")
    public String displayContactUs() {

        return "/client/core/contactUs";
    }
    
    @PostMapping("/contact")
    public String processResponse(@Valid ContactUsForm contactUsForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/client/core/contactUs";
        }
        contactUsForm.createContactMessage(contactMessageRepository);
        return "redirect:/";
    }
    
    @ModelAttribute("contactUsForm")
    public ContactUsForm contactUsForm(Principal principal){
         return new ContactUsForm();
    }
    
}
