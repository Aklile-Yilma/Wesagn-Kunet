package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.Arrays;

import com.gov.wesagnkunet.admin.data.models.BirthCertificateRequest;
import com.gov.wesagnkunet.admin.data.models.CertificateRequestDetails;
import com.gov.wesagnkunet.admin.data.repositories.BirthCertificateRequestRepository;
import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.CertificateDetails;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ChildInformation;
import com.gov.wesagnkunet.client.data.models.BirthCertificate.ParentInformation;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BirthApprovalController extends AdminDashboardController{

	@Autowired
	private BirthCertificateRequestRepository birthCertificateRequestRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping("/admin/dashboard/requests/birth")
	public String displayMarriageForm(Principal principal, ModelMap modelMap){

		modelMap.addAttribute("birthRequests", birthCertificateRequestRepository.findByCertificateRequestDetailsApproved(false));

		modelMap.addAttribute(
			"birthRequests", Arrays.asList(
				new BirthCertificateRequest(
					1l,
					new CertificateRequestDetails(
						clientRepository.findAll().iterator().next()
					),
					new ChildInformation(
						new Name("Abreham", "Atlaw", "Alemu"),
						new Date(System.currentTimeMillis()),
						Client.Sex.MALE,
						"1642605069020_00.jpg",
						new Address(
							countryRepository.findAll().iterator().next(),
							"Addis Ababa",
							"Yeka",
							4,
							234
						),
						"Ethiopian"
					),
					new ParentInformation(
						new Name("Hiwot", "Abreham", "Asegid"),
						"Ethiopian"
					),
					new ParentInformation(
						new Name("Atlaw", "Alemu", "Tarekegn"),
						"Ethiopian"
					)
				)
			)
		);

		return "/admin/dashboard/birth.html";
	}

}
