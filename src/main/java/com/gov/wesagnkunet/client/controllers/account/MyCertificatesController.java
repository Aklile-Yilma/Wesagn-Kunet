package com.gov.wesagnkunet.client.controllers.account;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.gov.wesagnkunet.client.controllers.ClientController;
import com.gov.wesagnkunet.client.data.models.Certificate;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.repositories.BirthCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.DeathCertificateRepository;
import com.gov.wesagnkunet.client.data.repositories.MarriageCertificateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MyCertificatesController extends ClientController{

	
	@Autowired
	private MarriageCertificateRepository marriageCertificateRepository;

	@Autowired
	private BirthCertificateRepository birthCertificateRepository;

	@Autowired
	private DeathCertificateRepository deathCertificateRepository;

	@GetMapping("/account/my-certificates")
	public String displayMyCertificates(){
		return "client/account/my_certificates.html";
	}

	@ModelAttribute(name = "certificates")
	public List<Certificate> certificates(Principal principal){

		Client client = getClient(principal);

		List<Certificate> certificates = new ArrayList<>();
		certificates.addAll(
			marriageCertificateRepository.findByCertificateDetailsOwnersContains(client)
		);
		certificates.addAll(
			birthCertificateRepository.findByCertificateDetailsOwnersContains(client)
		);
		certificates.addAll(
			deathCertificateRepository.findByCertificateDetailsOwnersContains(client)
		);

		return certificates;

	}

}
