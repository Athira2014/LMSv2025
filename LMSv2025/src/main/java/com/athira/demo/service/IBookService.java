package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.dto.BookCategoryAuthorDto;
import com.athira.demo.entity.Book;

public interface IBookService {

	List<Book> getAllBooks();

	Optional<Book> findBookById(int id);

	List<Book> findBookByName(String name);

	List<BookCategoryAuthorDto> getAllBookDto();

	Object saveBook(Book book);

	void deleteBookById(int id);

}
