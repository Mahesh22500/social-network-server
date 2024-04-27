package com.atlassian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.models.Story;
import com.atlassian.models.User;
import com.atlassian.service.StoryService;
import com.atlassian.service.UserService;

@RestController
public class StoryController {

	
	@Autowired
	private StoryService storyService;
	
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,  @RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		return storyService.createStory(story, user);
	}
	
	@GetMapping("/api/story/{userId}")
	public List<Story> findStoryByUserId(@PathVariable Integer userId) throws Exception{
		
		
		return storyService.findStoryByUserId(userId);
		
	}
	
}
