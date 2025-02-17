package com.athira.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Author;
import com.athira.demo.service.IAuthorService;
import com.athira.demo.util.JwtUtils;

@RestController
@RequestMapping("api/")
public class AuthorController {
	
	@Autowired
	IAuthorService authorService;
	
	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@GetMapping("authors")
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	//get an author by id
	@GetMapping("authors/search/{id}")
	public Optional<Author> getAuthorById(@PathVariable Integer id) {
		return authorService.getAuthorById(id);
	}
	
	// get Author by name
	@GetMapping("authors/{name}")
	public List<Author> findAthorByName(@PathVariable String name) {
		return authorService.findAthorByName(name);
		
	}
	
	//Create an author
	@PostMapping("authors")
	public ResponseEntity<APIResponse> addAuthor(@RequestBody Author author,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		APIResponse apiResponse = new APIResponse();

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		if(authorService.saveAuthor(author) == null) {
			apiResponse.setData("Author name should contain only alphabets.");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID AUTHOR NAME");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Author created succesfully!");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	/*
	 * //Create an author
	 * 
	 * @PostMapping("authors") public Author addAuthor(@RequestBody Author author) {
	 * return authorService.addAuthor(author); }
	 */

	//Update author
	@PutMapping("authors")
	public ResponseEntity<APIResponse> updateAuthor(@RequestBody Author author,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		APIResponse apiResponse = new APIResponse();

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}
		
		if(authorService.saveAuthor(author) == null) {
			apiResponse.setData("Author name should contain only alphabets.");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID AUTHOR NAME");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Author updated succesfully.");
		apiResponse.setStatus(200);
		apiResponse.setError(null);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	/*
	 * //Update author
	 * 
	 * @PutMapping("authors") public Author editAuthor(@RequestBody Author author) {
	 * return authorService.editAuthor(author); }
	 */
	
	// delete permenantly
	@DeleteMapping("authors/{id}")
	public void deleteAuthorById(@PathVariable int id) {
		authorService.deleteAuthorById(id);
	}

}
