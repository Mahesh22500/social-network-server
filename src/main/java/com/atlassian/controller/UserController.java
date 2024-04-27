package com.atlassian.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.exceptions.UserException;
import com.atlassian.models.User;
import com.atlassian.repository.UserRepository;
import com.atlassian.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		User savedUser =userService.registerUser(user);
		
		return savedUser;
	}
   
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users =userRepository.findAll();
		return users;
		
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		
		User user = userService.findUserById(id);
		return user;
	}
	
	
	
	
	@PutMapping("/api/users/update")
	public User updateUser(@RequestHeader ("Authorization") String jwt, @RequestBody User user) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user,reqUser.getId());
		
		return updatedUser;
		
	}
	
	@DeleteMapping("users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws UserException {
Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserException("user does not exist");
		}
		
		userRepository.delete(user.get());
		
		return "user deleted successfully with id";
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader ("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User>users = userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader ("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
		
	}
	
	
}
