package com.gov.wesagnkunet.admin.controllers;

import java.security.Principal;
import java.util.Arrays;

import com.gov.wesagnkunet.admin.data.models.Admin;
import com.gov.wesagnkunet.admin.data.repositories.AdminRepository;
import com.gov.wesagnkunet.lib.WesagnKunetController;
import com.gov.wesagnkunet.lib.auth.data.repositories.UserRepository;
import com.gov.wesagnkunet.lib.webcontent.data.models.Tab;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class AdminController extends WesagnKunetController{
	
	@Autowired
	private TabRepository tabRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@ModelAttribute
	protected void setupModelMap(ModelMap modelMap){
		modelMap.addAttribute("sideTabs", 
			tabRepository.findByClazzOrderByRelativeOrderAsc("admin_side")
		);
	}

	@ModelAttribute("admin")
	protected Admin admin(Principal principal){
		return adminRepository.findByUser(
			userRepository.findByUsername(principal.getName())
		);
	}
}
