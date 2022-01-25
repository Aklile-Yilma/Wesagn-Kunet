package com.gov.wesagnkunet.client.controllers.core;

import javax.validation.Valid;

import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactUsController  extends ClientController{



    @GetMapping("/contact")
    public String displayContactUs() {

        return "/client/core/contactUs";
    }
    
    @PostMapping("/contact")
    public String processResponse(/*@Valid ContactUsForm contactUsForm*/ BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/client/core/contactUs";
        }
        //ContactUsRepository.save(contactUsForm);
        return "redirect:/";
        //TODO: process POST request
    }
    
    
}
