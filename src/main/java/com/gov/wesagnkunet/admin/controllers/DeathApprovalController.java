package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;

import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DeathApprovalController extends AdminDashboardController{

	@Autowired
	private DeathCertificateRequestRepository deathCertificateRequestRepository;

	@GetMapping("/admin/dashboard/requests/death")
	public String displayMarriageForm(Principal principal, ModelMap modelMap){

		modelMap.addAttribute("deathRequests", deathCertificateRequestRepository.findByCertificateRequestDetailsApproved(false));

		return "/admin/dashboard/death.html";
	}
	
}
