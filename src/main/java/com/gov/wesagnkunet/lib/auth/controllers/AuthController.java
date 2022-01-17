package com.gov.wesagnkunet.lib.auth.controllers;

import com.gov.wesagnkunet.lib.WesagnKunetController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController extends WesagnKunetController{

	@GetMapping("/login")
	public String handleLogin(@RequestParam(value = "error", required = false) Object error, ModelMap modelMap){
		

		modelMap.addAttribute("loginError", (error != null));
	
		return "/lib/auth/login.html";

	}
	
}
