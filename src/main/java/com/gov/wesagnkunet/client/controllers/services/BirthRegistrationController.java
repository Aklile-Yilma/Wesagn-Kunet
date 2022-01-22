package com.gov.wesagnkunet.client.controllers.services;

import java.security.Principal;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.services.forms.BirthRegistrationForm;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class BirthRegistrationController extends ClientController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private BirthCertificateRequestRepository birthCertificateRequestRepository;


    @GetMapping("/registration/birth")
    public String displayBirthform() {
        return "/client/registration/birth-registration";
    }

    @PostMapping("registration/birth")
    public String handleBirthRegistrationForm(@Valid BirthRegistrationForm birthRegistrationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/client/registration/birth-registration";
        }
        birthRegistrationForm.createCertificateRequest();
        return "redirect:/requestSeccessfull";

    }

    @ModelAttribute("birthRegistrationForm")
    private BirthRegistrationForm birthRegistrationForm(Principal principal){
        return new BirthRegistrationForm(getClient(principal), storageService, birthCertificateRequestRepository);
    }


  
    
    
}
