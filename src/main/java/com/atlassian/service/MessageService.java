package com.atlassian.service;

import java.util.List;

import com.atlassian.models.Chat;
import com.atlassian.models.Message;
import com.atlassian.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message message) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
	
}
