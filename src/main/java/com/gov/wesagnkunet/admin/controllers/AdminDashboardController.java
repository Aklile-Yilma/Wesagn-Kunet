package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import com.gov.wesagnkunet.admin.data.models.Admin;
import com.gov.wesagnkunet.admin.data.repositories.AdminRepository;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;
import com.gov.wesagnkunet.lib.WesagnKunetController;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class AdminDashboardController extends WesagnKunetController{
	
	@Autowired
	private TabRepository tabRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BirthCertificateRequestRepository birthCertificateRequestRepository;

	@Autowired
	private MarriageCertificateRequestRepository marriageCertificateRequestRepository;

	@Autowired
	private DeathCertificateRequestRepository deathCertificateRequestRepository;

	@ModelAttribute
	protected void setupModelMap(ModelMap modelMap){
		modelMap.addAttribute("sideTabs", 
			tabRepository.findByClazzOrderByRelativeOrderAsc("admin_side")
		);
	}

	@ModelAttribute("admin")
	protected Admin admin(Principal principal){
		return adminRepository.findByClientUser(
			userRepository.findByUsername(principal.getName())
		);
	}

	@ModelAttribute("notifications")
	protected Map<String, Long> notifications(){
		HashMap<String, Long> notifications = new HashMap<String, Long>();
		notifications.put(
			"Birth Certificates", birthCertificateRequestRepository.countByCertificateRequestDetailsApproved(false)
		);
		notifications.put(
			"Marriage Certificates", marriageCertificateRequestRepository.countByCertificateRequestDetailsApproved(false)
		);
		notifications.put(
			"Death Certificates", deathCertificateRequestRepository.countByCertificateRequestDetailsApproved(false)
		);
		return notifications;
	}
}
