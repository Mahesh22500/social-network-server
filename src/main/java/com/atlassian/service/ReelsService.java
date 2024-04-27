package com.atlassian.service;

import java.util.List;

import com.atlassian.models.Reels;
import com.atlassian.models.User;

public interface ReelsService {

	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
}
