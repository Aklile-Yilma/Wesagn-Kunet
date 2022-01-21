package com.gov.wesagnkunet.client.controllers.core;

import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ContactUsController  extends ClientController{

    @GetMapping("/contact")
    public String displayContactUs() {
        return "/client/core/contactUs";
    }
    
    @PostMapping("/contactUs")
    public String processResponse(){
        //TODO: process POST request
        
        return "redirect:/";
    }
    
    
}
