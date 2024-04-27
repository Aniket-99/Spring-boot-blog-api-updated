package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.PostDTO;
import com.blog.api.payloads.PostResponse;

public interface PostService {
	
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String order);
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	List<PostDTO> getPostsByUser(Integer userId);
	
	List<PostDTO> searchPosts(String keyword);

}
