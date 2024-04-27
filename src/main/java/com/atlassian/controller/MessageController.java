package com.atlassian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.models.Message;
import com.atlassian.models.User;
import com.atlassian.service.MessageService;
import com.atlassian.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
//	public Message createMessage(User user, Integer chatId, Message message) throws Exception;
//	
//	public List<Message> findChatsMessages(Integer chatId) throws Exception;

	
	@PostMapping("api/messages/chat/{chatId}")
	public Message createMessage(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt,@RequestBody Message req) throws Exception
	{
		User user = userService.findUserByJwt(jwt);
		
		return messageService.createMessage(user, chatId, req);
		
		
	}
	
	@GetMapping("api/messages/chat/{chatId}")
	public List<Message> findChatsMessages(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwt(jwt);
		return messageService.findChatsMessages(chatId);
	}
}
