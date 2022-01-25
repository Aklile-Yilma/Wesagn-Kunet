package com.gov.wesagnkunet.client.controllers.auth;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.controllers.auth.forms.SignUpForm;
import com.gov.wesagnkunet.client.data.models.Client.BloodType;
import com.gov.wesagnkunet.client.data.models.Client.Sex;
import com.gov.wesagnkunet.client.data.models.DeathCertificate.Title;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.lib.auth.UserManager;
import com.gov.wesagnkunet.lib.auth.exceptions.UserExistsException;
import com.gov.wesagnkunet.lib.core.exceptions.InternalServerError;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SignUpController extends ClientController{

	@Autowired
	private UserManager userManager;

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private ClientRepository clientRepository;


	@GetMapping("/auth/sign-up")
	public String displaySignUpForm(ModelMap modelMap){

		return "client/auth/signup.html";
	}

	@PostMapping("/auth/sign-up")
	public String handleSignUpForm(@Valid SignUpForm signUpForm, BindingResult bindingResult){

		if(bindingResult.hasErrors())
			return "client/auth/signup.html";
		
		try{
			signUpForm.createClient();
		}
		catch(UserExistsException ex){
			throw new InternalServerError();
		}
		return "redirect:/";

	}

	@ModelAttribute("signUpForm")
	private SignUpForm signUpForm(){
		return new SignUpForm(userManager, storageService, clientRepository);
	}

	@ModelAttribute("bloodTypes")
	private List<BloodType> bloodTypes(){
		return Arrays.asList(BloodType.values());
	}

	@ModelAttribute("sexes")
	private List<Sex> sexes() {
		return Arrays.asList(Sex.values());
	}

	@ModelAttribute("titles")
	private List<Title> titles() {
		return Arrays.asList(Title.values());
	}



	
}
