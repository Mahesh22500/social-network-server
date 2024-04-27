package com.atlassian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.models.Reels;
import com.atlassian.models.User;
import com.atlassian.service.ReelsService;
import com.atlassian.service.UserService;

@RestController
public class ReelsController {

	
	@Autowired
	private ReelsService reelsService;
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		
		User reqUser = userService.findUserByJwt(jwt);
		Reels createdReels = reelsService.createReel(reel, reqUser);
		
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReel( @PathVariable Integer userId) throws Exception {
		
		
		
		return reelsService.findUsersReel(userId);
	
	
	}
	
	
	
	
}
