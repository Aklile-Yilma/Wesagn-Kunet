package com.gov.wesagnkunet.client.controllers.services;

import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.data.models.BirthCertificate;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class BirthRegistrationController extends ClientController {
    
    private BirthCertificateRepository repository; //for accessing the repository

    @GetMapping("/birth")
    public String displayBirthform() {
        return "/client/registration/birthForm";
    }

  
    
    
}
