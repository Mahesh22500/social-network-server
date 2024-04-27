package com.atlassian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.models.Comment;
import com.atlassian.models.User;
import com.atlassian.service.CommentService;
import com.atlassian.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Comment createdComment = commentService.createComment(comment,postId,reqUser.getId());
		
		return  createdComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		return commentService.likeComment(commentId,reqUser.getId());
		
		
	}
	
}
