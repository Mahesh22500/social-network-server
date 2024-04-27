package com.atlassian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContoller {

	@GetMapping("/home")
	public String homeControllerHandler() {
		return "this is home";
	}
}
