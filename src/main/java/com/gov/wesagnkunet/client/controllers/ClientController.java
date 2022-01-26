package com.gov.wesagnkunet.client.controllers;


import java.security.Principal;
import java.util.List;

import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;
import com.gov.wesagnkunet.client.data.repositories.NationalityRepository;
import com.gov.wesagnkunet.lib.WesagnKunetController;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class ClientController extends WesagnKunetController{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private TabRepository tabRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private NationalityRepository nationalityRepository;

	@ModelAttribute("client")
	public Client getClient(Principal principal){
		if(principal == null)
			return null;
		
		return clientRepository.findByUser(
			userRepository.findByUsername(principal.getName())
		);

	}

	@ModelAttribute
	public void setupModelMap(ModelMap modelMap){

		modelMap.addAttribute("headerTabs", tabRepository.findByClazzAndParentTabOrderByRelativeOrderAsc("client_header", null));
		modelMap.addAttribute("footerTabs", tabRepository.findByClazzAndParentTabOrderByRelativeOrderAsc("client_footer", null));
		modelMap.addAttribute("footerTabsServices", tabRepository.findByClazzAndParentTabOrderByRelativeOrderAsc("client_footer_service", null));
		
	}

	@ModelAttribute(name = "countries")
	private Iterable<Country> countries() {
		return countryRepository.findAll();
	}
	
	@ModelAttribute(name = "nationalities")
	private Iterable<Nationality> nationalities() {
		return nationalityRepository.findAll();
	}

}
