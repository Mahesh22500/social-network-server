package com.atlassian.service;

import com.atlassian.models.Comment;

public interface CommentService {

	
	public Comment createComment(Comment comment, Integer postId, Integer userId ) throws Exception;
	
	public Comment deleteComment(Integer commendId, Integer postId, Integer userId) throws Exception;
	
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer  commentId) throws Exception;
}
