package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;

import javax.validation.Valid;

import com.gov.wesagnkunet.admin.controllers.forms.MarriageApprovalForm;
import com.gov.wesagnkunet.admin.data.repositories.MarriageCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.repositories.CertificateDetailsRepository;
import com.gov.wesagnkunet.client.data.repositories.MarriageCertificateRepository;
import com.gov.wesagnkunet.lib.core.exceptions.InternalServerError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RequestController extends AdminController{

	@Autowired
	private MarriageCertificateRequestRepository marriageCertificateRequestRepository;

	@Autowired
	private MarriageCertificateRepository marriageCertificateRepository;

	@Autowired
	private CertificateDetailsRepository certificateDetailsRepository;

	
	@GetMapping("/admin/dashboard/request")
	public String displayMarriageForm(Principal principal, ModelMap modelMap){

		modelMap.addAttribute("marriageRequests", marriageCertificateRequestRepository.findByCertificateRequestDetailsApproved(false));

		return "/admin/dashboard/marriage.html";
	}

	@PostMapping("/admin/dashboard/request/marriage")
	public String handleApproval(
		@Valid MarriageApprovalForm marriageApprovalForm,
		BindingResult bindingResult
	){

		if(bindingResult.hasErrors())
			throw new InternalServerError();
		
		marriageApprovalForm.handle();

		return "redirect:/admin/dashboard/request/";
	}

	@ModelAttribute("marriageApprovalForm")
	public MarriageApprovalForm marriageApprovalForm(){
		return new MarriageApprovalForm(marriageCertificateRepository, certificateDetailsRepository, marriageCertificateRequestRepository);
	}
	
}
