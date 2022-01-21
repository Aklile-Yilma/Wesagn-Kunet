package com.gov.wesagnkunet.lib;

import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class WesagnKunetController {

	@Autowired
	private FileStorageService storageService;
	
	@ModelAttribute(name="media")
	private FileStorageService fileStorageService(){
		return storageService;
	}
	
}
