package com.tunehub.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//This controller class is used only for mapping with the view

@Controller
public class NavController {
	
	//This method is used to connect to register page from view through controller
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}
	
	//This method is used to connect to login page from view through controller
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	
	@GetMapping("/map-songs")
	public String songMapping() {
		return "addsongs";
	}
	@GetMapping("/samplepayment")
	public String samplePayment() {
		return "samplepayment";
	}
}
