package com.atlassian.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.atlassian.request.LoginRequest;
import com.atlassian.config.JwtProvider;
import com.atlassian.models.User;
import com.atlassian.repository.UserRepository;
import com.atlassian.response.AuthResponse;
import com.atlassian.service.CustomerUserDetailsService;
import com.atlassian.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {



	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerUserDetailsService customerUserDetails;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		System.out.println("this api has been called");
		User isExist =userRepository.findByEmail(user.getEmail());

		if(isExist != null) {
			throw new Exception("email exists");
		}

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token,"Register successfully");

		return res;
	}


	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token,"Logged in successfully");

		return res;
	}



	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);

		if(userDetails == null) {
			throw new BadCredentialsException("invalid username");
		}

		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
