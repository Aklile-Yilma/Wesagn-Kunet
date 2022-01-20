package com.gov.wesagnkunet.client.controllers.registration;

import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarriageRegistrationController extends ClientController {

    @GetMapping("/marriage")
    public String displayMarriageForm() {
        return "/client/registration/marriage-registration";
    }
    
}
