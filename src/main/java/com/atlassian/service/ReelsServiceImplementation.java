package com.atlassian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.models.Reels;
import com.atlassian.models.User;
import com.atlassian.repository.ReelsRepository;


@Service
public class ReelsServiceImplementation implements ReelsService{

	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Reels createReel(Reels reel, User user) {
		
		Reels newReel = new Reels();
		
		newReel.setId(reel.getId());
		newReel.setTitle(reel.getTitle());
		newReel.setVideo(reel.getVideo());
		newReel.setUser(user);
		return reelsRepository.save(newReel);
		
	}

	@Override
	public List<Reels> findAllReels() {

		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {

        userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
