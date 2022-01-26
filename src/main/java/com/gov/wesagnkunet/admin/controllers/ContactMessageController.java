package com.gov.wesagnkunet.admin.controllers;

import java.util.Arrays;

import com.gov.wesagnkunet.admin.data.models.ContactMessage;
import com.gov.wesagnkunet.admin.data.repositories.ContactMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ContactMessageController extends AdminDashboardController{

	@Autowired
	private ContactMessageRepository contactMessageRepository;

	@GetMapping("/admin/dashboard/contact")
	public String displayContactMessages(
		ModelMap modelMap
	){

		modelMap.addAttribute(
			"messages", contactMessageRepository.findAllByOrderByDateDesc()
		);

		return "admin/dashboard/contact.html";

	}
	
}
