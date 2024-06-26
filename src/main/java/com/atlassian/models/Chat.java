package com.atlassian.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chat {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String chat_name;
	
	private String chat_image;
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	
	private LocalDateTime timestamp;
	
	@OneToMany(mappedBy ="chat")
	private List<Message> messages = new ArrayList<>();
}
