package com.gov.wesagnkunet.client.controllers.services;

import java.security.Principal;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.services.forms.MarriageRegistrationForm;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MarriageRegistrationController extends ClientController{

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private MarriageCertificateRequestRepository marriageCertificateRequestRepository;

	@GetMapping("/registration/marriage")
	public String displayMarriageRegistrationForm(){

		 return "/client/registration/marriage-registration.html";
	}

	@PostMapping("/registration/marriage")
	public String handleMarriageRegistrationForm(
		@Valid MarriageRegistrationForm marriageRegistrationForm,
		BindingResult bindingResult
	){

		if(bindingResult.hasErrors())
			return "/client/registration/marriage-registration.html";

		marriageRegistrationForm.createCertificateRequest();

		return "redirect:/?requestSuccessfull";
	}

	@ModelAttribute("marriageRegistrationForm")
	private MarriageRegistrationForm marriageRegistrationForm(Principal principal){
		return new MarriageRegistrationForm(getClient(principal), storageService, marriageCertificateRequestRepository);
	}
	

}
