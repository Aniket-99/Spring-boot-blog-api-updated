package com.blog.api.services;

import com.blog.api.payloads.CommentDTO;

public interface CommentService {

	
	CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId);
	
	void deleteComment(Integer commentId);
	
	
}
