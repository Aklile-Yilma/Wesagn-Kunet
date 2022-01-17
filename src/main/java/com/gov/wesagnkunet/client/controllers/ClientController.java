package com.gov.wesagnkunet.client.controllers;


import java.security.Principal;

import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class ClientController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private TabRepository tabRepository;

	@ModelAttribute
	public Client addClient(Principal principal){
		if(principal == null)
			return null;
		
		return clientRepository.findByUser(
			userRepository.findByUsername(principal.getName())
		);

	}

	@ModelAttribute
	public void setupModelMap(ModelMap modelMap){

		modelMap.addAttribute("headerTabs", tabRepository.findByClazzAndParentTab("client_header", null));
		modelMap.addAttribute("footerTabs", tabRepository.findByClazzAndParentTab("client_footer", null));
		modelMap.addAttribute("footerTabsServices", tabRepository.findByClazzAndParentTab("client_footer_service", null));
		
	}

}
