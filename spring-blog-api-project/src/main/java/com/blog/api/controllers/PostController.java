package com.blog.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.config.AppConstants;
import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.PostDTO;
import com.blog.api.payloads.PostResponse;
import com.blog.api.services.FileService;
import com.blog.api.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.images}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		PostDTO post = this.postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.SORT_BY) String sortBy,
			@RequestParam(value = "order", required = false, defaultValue = AppConstants.ORDER) String order) {
		PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, order);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/postById/{postId}")
	public ResponseEntity<PostDTO> getSinglePost(@PathVariable("postId") Integer postId) {
		PostDTO postDTO = this.postService.getPostById(postId);
		return ResponseEntity.ok(postDTO);
	}

	@GetMapping("/postByCategoryId/{categoryId}")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostDTO> allPosts = this.postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/postByUserId/{userId}")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("userId") Integer userId) {
		List<PostDTO> allPosts = this.postService.getPostsByUser(userId);
		return ResponseEntity.ok(allPosts);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable("postId") Integer postId, @RequestBody PostDTO postDTO) {
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.CREATED);
	}

	@GetMapping("/searchPostByTitle")
	public ResponseEntity<?> searchPost(@RequestParam("keyword") String keyword) {
		List<PostDTO> posts = this.postService.searchPosts(keyword);

		if (posts.isEmpty()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("No Post found with title " + keyword, false),
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(posts);
	}

	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") int postId) throws IOException {

		PostDTO postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName); 
		PostDTO updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);

	}

	@GetMapping(value = "/image/{imageName}", produces = { MediaType.IMAGE_PNG_VALUE })
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);

		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
