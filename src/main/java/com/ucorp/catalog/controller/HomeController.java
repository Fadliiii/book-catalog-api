package com.ucorp.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


//	@RequestMapping(value = "/home",method = RequestMethod.GET)
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("name","umam");
		return "home";
	}
	
}
