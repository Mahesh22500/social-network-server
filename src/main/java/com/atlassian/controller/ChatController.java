package com.atlassian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.models.Chat;
import com.atlassian.models.User;
import com.atlassian.request.CreateChatRequest;
import com.atlassian.service.ChatService;
import com.atlassian.service.UserService;

@RestController
public class ChatController {

	
	@Autowired
	private ChatService chatService;
	
	
	@Autowired
	private UserService userService;
	

	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestBody CreateChatRequest req,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user2 =userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser,user2);
		return chat;
	}
	
	
	@GetMapping("/api/chats/{chatId}")
	public Chat findChatById(@PathVariable Integer chatId) throws Exception{
		
		return chatService.findChatById(chatId);
		
	}
	
	
	@GetMapping("api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
		User user = userService.findUserByJwt(jwt);
		return chatService.findUsersChat(user.getId());
	}
	
	
//	public Chat 
}
