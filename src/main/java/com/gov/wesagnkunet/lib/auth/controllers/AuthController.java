package com.gov.wesagnkunet.lib.auth.controllers;

import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController extends ClientController{

	@GetMapping("/auth/login")
	public String handleLogin(@RequestParam(value = "error", required = false) Object error, ModelMap modelMap){
		
		if(error != null)
			modelMap.addAttribute("loginError", (error != null));
	
		return "/lib/auth/login.html";

	}
	
}
