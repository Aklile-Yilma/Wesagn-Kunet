package com.gov.wesagnkunet.client.controllers.services;

import java.security.Principal;
import java.util.List;
import java.util.Arrays;
import javax.validation.Valid;

import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.services.forms.BirthRegistrationForm;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;
import com.gov.wesagnkunet.client.data.models.Client.Sex;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class BirthRegistrationController extends ClientController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private BirthCertificateRequestRepository birthCertificateRequestRepository;

    
	@Autowired
	private TabRepository tabRepository;


    @GetMapping("/registration/birth")
    public String displayBirthform() {
        return "/client/registration/birth-registration";
    }

    @PostMapping("/registration/birth")
    public String handleBirthRegistrationForm(@Valid BirthRegistrationForm birthRegistrationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/client/registration/birth-registration";
        }

		log.info("Here");

        birthRegistrationForm.createCertificateRequest();
        return "redirect:/?requestSeccessfull";

    }

    
    @ModelAttribute("birthRegistrationForm")
    private BirthRegistrationForm birthRegistrationForm(Principal principal){
        return new BirthRegistrationForm(getClient(principal), storageService, birthCertificateRequestRepository);
    }

    @ModelAttribute("sexes")
    private List<Sex> sex(){
        return Arrays.asList(Sex.values());
    }


  
    
    
}
