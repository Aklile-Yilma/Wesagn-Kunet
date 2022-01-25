package com.gov.wesagnkunet.client.controllers.core;
import com.gov.wesagnkunet.client.controllers.ClientController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutUsController extends ClientController {


    @GetMapping("/about")
    public String displayAboutUs() {
        return "/client/core/about.html";
    }
    
    
}
