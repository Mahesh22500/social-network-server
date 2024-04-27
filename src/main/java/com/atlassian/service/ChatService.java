package com.atlassian.service;

import java.util.List;

import com.atlassian.models.Chat;
import com.atlassian.models.User;

public interface ChatService {

	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
