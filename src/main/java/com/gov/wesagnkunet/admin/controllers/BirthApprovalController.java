package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.controllers.forms.BirthApprovalForm;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;
import com.gov.wesagnkunet.lib.core.exceptions.InternalServerError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BirthApprovalController extends AdminDashboardController{

	@Autowired
	private BirthCertificateRequestRepository birthCertificateRequestRepository;

	@Autowired
	private BirthCertificateRepository birthCertificateRepository;

	@Autowired
	private CertificateDetailsRepository certificateDetailsRepository;


	@GetMapping("/admin/dashboard/requests/birth")
	public String displayMarriageForm(Principal principal, ModelMap modelMap){

		modelMap.addAttribute("birthRequests", birthCertificateRequestRepository.findByCertificateRequestDetailsApproved(false));

		return "/admin/dashboard/birth.html";
	}

	@PostMapping("/admin/dashboard/requests/birth")
	public String handleApproval(
		@Valid BirthApprovalForm birthApprovalForm,
		BindingResult bindingResult
	){

		if(bindingResult.hasErrors())
			throw new InternalServerError();
		
		birthApprovalForm.handle();

		return "redirect:/admin/dashboard/requests/birth";
	}

	@ModelAttribute("birthApprovalForm")
	public BirthApprovalForm birthApprovalForm(){
		return new BirthApprovalForm(birthCertificateRepository, certificateDetailsRepository, birthCertificateRequestRepository);
	}

}
