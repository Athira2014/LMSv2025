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
import com.athira.demo.dto.BookCategoryAuthorDto;
import com.athira.demo.entity.Book;
import com.athira.demo.service.IBookService;
import com.athira.demo.util.JwtUtils;

@RestController
@RequestMapping("api/")
public class BookController {
	
	@Autowired
	IBookService bookService;

	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@GetMapping("books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("books/{id}")
	public Optional<Book> findBookById(@PathVariable int id){
		return bookService.findBookById(id);
	}
	
	@GetMapping("books/search/{name}")
	public List<Book> findBookByName(@PathVariable String name){
		return bookService.findBookByName(name);
	}
	
	@GetMapping("books/dto")
	public List<BookCategoryAuthorDto> getAllBookDto(){
		return bookService.getAllBookDto();
	}
	
	@PostMapping("books")
	public ResponseEntity<APIResponse> saveBook(@RequestBody Book book,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		APIResponse apiResponse = new APIResponse();

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		
		if(bookService.saveBook(book) == null) {
			apiResponse.setStatus(500);
			apiResponse.setData("Book name should contain only alphabets.");
			apiResponse.setError("Name Error");
		}
		apiResponse.setError(null);
		apiResponse.setStatus(200);
		apiResponse.setData("Book added succesfully!");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
	}
	
	@PutMapping("books")
	public ResponseEntity<APIResponse> updateBook(@RequestBody Book book,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		APIResponse apiResponse = new APIResponse();

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		
		if(bookService.saveBook(book) == null) {
			apiResponse.setStatus(500);
			apiResponse.setData("Book name should contain only alphabets.");
			apiResponse.setError("Name Error");
		}
		apiResponse.setError(null);
		apiResponse.setStatus(200);
		apiResponse.setData("Book added succesfully!");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@DeleteMapping("books/{id}")
	private void deleteBookById(@PathVariable int id) {
		bookService.deleteBookById(id);
	}
}
