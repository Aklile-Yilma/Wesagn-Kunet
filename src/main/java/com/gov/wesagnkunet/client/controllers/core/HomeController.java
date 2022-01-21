package com.gov.wesagnkunet.client.controllers.core;

import java.util.Arrays;
import java.util.List;

import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;
import com.gov.wesagnkunet.lib.webcontent.data.models.Tab;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController extends ClientController{

	@Autowired
	private TabRepository tabRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@GetMapping("/")
	public String displayHome(){
	
		return "/client/home/home.html";
	}
	
}
