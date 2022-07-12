package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commnetDto, Integer postId) ;
	void deleteComment(Integer commentId);
}
