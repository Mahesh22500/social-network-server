package com.atlassian.repository;

import java.util.List;

import com.atlassian.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atlassian.models.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository  extends JpaRepository<Chat,Integer> {

	
	public List<Chat> findByUsersId(Integer userId);

	@Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
	
}
