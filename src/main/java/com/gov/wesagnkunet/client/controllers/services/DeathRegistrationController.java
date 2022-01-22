package com.gov.wesagnkunet.client.controllers.services;

import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeathRegistrationController extends ClientController {


    @GetMapping("/registration/death")
    public String showDeathRegistrationForm() {
        return "/client/registration/death-registration";
    }
    
}
