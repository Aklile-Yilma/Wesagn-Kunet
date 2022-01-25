package com.gov.wesagnkunet.client.controllers.core;

import java.util.HashMap;
import java.util.Map;

import java.sql.Date;

import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.data.models.BirthCertificate;
import com.gov.wesagnkunet.client.data.models.DeathCertificate;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.MarriageCertificateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController extends ClientController{

	@Autowired
	private BirthCertificateRepository birthCertificateRepository;

	@Autowired
	private MarriageCertificateRepository marriageCertificateRepository;

	@Autowired
	private DeathCertificateRepository deathCertificateRepository;


	@GetMapping("/")
	public String displayHome(ModelMap modelMap){
		
		Date targetDate = getTargetDate();

		modelMap.addAttribute("birthStats", birthCertificateRepository.countByCertificateDetailsIssueDateGreaterThan(targetDate));
		modelMap.addAttribute("marriageStats", marriageCertificateRepository.countByCertificateDetailsIssueDateGreaterThan(targetDate));
		modelMap.addAttribute("deathStats", deathCertificateRepository.countByCertificateDetailsIssueDateGreaterThan(targetDate));
		modelMap.addAttribute("totalStats", 
			((Long)modelMap.getAttribute("birthStats"))+((Long)modelMap.getAttribute("marriageStats"))+((Long)modelMap.getAttribute("deathStats"))
		);

		return "/client/core/home.html";
	}

	private Date getTargetDate(){
		Date targetDate = new Date(0);
		targetDate.setYear(new Date(System.currentTimeMillis()).getYear());
		return targetDate;
	}

	
}
