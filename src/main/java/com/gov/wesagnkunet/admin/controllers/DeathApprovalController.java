package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.controllers.forms.DeathApprovalForm;
import com.gov.wesagnkunet.admin.data.repositories.DeathCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;
import com.gov.wesagnkunet.lib.core.exceptions.InternalServerError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DeathApprovalController extends AdminDashboardController{

	@Autowired
	private DeathCertificateRequestRepository deathCertificateRequestRepository;

	@Autowired
	private DeathCertificateRepository deathCertificateRepository;

	@Autowired
	private CertificateDetailsRepository certificateDetailsRepository;

	@GetMapping("/admin/dashboard/requests/death")
	public String displayMarriageForm(Principal principal, ModelMap modelMap){

		modelMap.addAttribute("deathRequests", deathCertificateRequestRepository.findByCertificateRequestDetailsApproved(false));

		return "/admin/dashboard/death.html";
	}

	@PostMapping("/admin/dashboard/requests/death")
	public String handleApproval(
		@Valid DeathApprovalForm deathApprovalForm,
		BindingResult bindingResult
	){

		if(bindingResult.hasErrors())
			throw new InternalServerError();
		
		deathApprovalForm.handle();

		return "redirect:/admin/dashboard/requests/death";
	}

	@ModelAttribute("deathApprovalForm")
	public DeathApprovalForm deathApprovalForm(){
		return new DeathApprovalForm(deathCertificateRepository, certificateDetailsRepository, deathCertificateRequestRepository);
	}
	
}
