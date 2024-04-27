package com.atlassian.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import com.atlassian.models.Message;
import com.atlassian.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private String message;
	private String error;
	
	private LocalDateTime timestamp;
	
	
}
