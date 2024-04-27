package com.atlassian.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.models.Chat;
import com.atlassian.models.Message;
import com.atlassian.models.User;
import com.atlassian.repository.ChatRepository;
import com.atlassian.repository.MessageRepository;


@Service
public class MessageServiceImplementation implements MessageService{

	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		Message message = new Message();
		Chat chat = chatService.findChatById(chatId);
		
		message.setChat(chat);;
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(req.getUser());
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage =  messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		Chat chat = chatService.findChatById(chatId);
		
		
		return messageRepository.findByChatId(chatId);
	}

	
	
}
