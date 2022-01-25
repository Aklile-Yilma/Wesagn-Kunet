package com.gov.wesagnkunet.client.controllers.services;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.services.forms.DeathRegistrationForm;
import com.gov.wesagnkunet.client.data.models.DeathCertificate.Title;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeathRegistrationController extends ClientController {
   
    @Autowired
    private FileStorageService storageService;

    @Autowired
    private DeathCertificateRequestRepository deathCertificateRequestRepository;
    
    private DeathCertificateRepository deathRepository;

     

    @GetMapping("registration/death")
    public String dislayDeathForm(ModelMap map) {

        DeathRegistrationForm deathRegistrationForm = new DeathRegistrationForm();

        map.addAttribute("deathRegistrationForm", deathRegistrationForm);
        return "/client/registration/death-registration";
    }

    @PostMapping("/registration/death")
    public String handleDeathRegistrationForm(
        @Valid DeathRegistrationForm deathRegistrationForm, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "/client/registration/death-registration";

        deathRegistrationForm.createCertificateRequest();
        return "redirect:/?requestSuccessfull";
    }
    
    @ModelAttribute("deathRegistrationForm")
    private DeathRegistrationForm deathRegistrationForm(Principal principal) {
        return new DeathRegistrationForm(getClient(principal), storageService, deathCertificateRequestRepository);
    }

	@ModelAttribute("titles")
	private List<Title> titles(){
		return Arrays.asList(Title.values());
	}
    
}
