package com.microservice.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.user.models.User;
import com.microservice.user.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@RequestMapping(value="/registerUser", method = RequestMethod.GET)
	public String form() {
		return "registerUser";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(String email, String password) {
		User user = repo.loginUser(email, password);
		if (user == null) return "error";
		return "welcome";
	}
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String form(User user) {
		repo.save(user);
		return "redirect:/registerUser";
	}
}
