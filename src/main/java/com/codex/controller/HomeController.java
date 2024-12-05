package com.codex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String home() {
		return "wellcome to treading platform";
	}
	
	@GetMapping("/apiw")
	public String secure() {
		return "wellcome to treading platform secure!!";
	}
}
