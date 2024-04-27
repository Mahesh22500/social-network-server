package com.atlassian.service;

import java.util.List;

import com.atlassian.models.Story;
import com.atlassian.models.User;

public interface  StoryService {

	
	public Story createStory (Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
