package com.rahil.blog.controller;

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

import com.rahil.blog.config.AppConstant;
import com.rahil.blog.payload.ApiResponse;
import com.rahil.blog.payload.PostDto;
import com.rahil.blog.payload.PostResponse;
import com.rahil.blog.service.FileService;
import com.rahil.blog.service.Postservice;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private Postservice postservice;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(
			@Valid @RequestBody PostDto dto ,
			@PathVariable("userId") Integer userId,
			@PathVariable ("categoryId") Integer categoryId ){
		
		PostDto createPost=this.postservice.createPost(dto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getByUserId(@PathVariable Integer userId){
		List<PostDto> posts=this.postservice.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getBycategory(@PathVariable Integer categoryId){
		List<PostDto> posts=this.postservice.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/getAll")
	public ResponseEntity<PostResponse> getAllPosts( 
			@RequestParam(value = "pageNumber", defaultValue =AppConstant.PAGE_NUMBER ,required  = false) Integer pageNumber, 
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE ,required = false) Integer pageSize,
			@RequestParam (value="sortBy",defaultValue = AppConstant.SORT_BY ,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR ,required = false) String sortDir){
		
		PostResponse postResponse=this.postservice.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getpostById(@PathVariable Integer postId){
		PostDto dto=this.postservice.getPostById(postId);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/post/update/{id}")
	public ResponseEntity<PostDto> updatePOst(@RequestBody PostDto postDto ,@PathVariable Integer id){
		PostDto dto=this.postservice.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/delete/{postId}")
	public  ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		this.postservice.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfull",true),HttpStatus.OK);
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostbyTitle(@PathVariable String keyword){
		List<PostDto> posts= this.postservice.SearchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto dto= this.postservice.getPostById(postId);
		
		String fileName= this.fileService.uploadImage(path, image);
		
		dto.setImageName(fileName);
		
	PostDto dto2=this.postservice.updatePost(dto, postId);
	
	return new ResponseEntity<PostDto>(dto2,HttpStatus.OK);
	}
	
	//method to serve file
	@GetMapping(value = "/post/image/{imagaName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imagaName") String imagaName ,
			HttpServletResponse response) throws IOException {
		
		InputStream resource=this.fileService.getResource(path, imagaName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
}
