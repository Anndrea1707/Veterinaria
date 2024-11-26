package com.parcial.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
	@GetMapping({"/","/index","/home"})
	public String redirectToHomePageString() {
		return "index";
	}
}
