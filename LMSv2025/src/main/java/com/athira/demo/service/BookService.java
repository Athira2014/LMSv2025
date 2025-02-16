package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.common.Validation;
import com.athira.demo.dao.IBookDao;
import com.athira.demo.dto.BookCategoryAuthorDto;
import com.athira.demo.entity.Book;

@Service
public class BookService implements IBookService {

	@Autowired
	IBookDao bookDao;

	@Autowired
	private Validation validation;
	
	@Transactional
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}

	@Transactional
	public Optional<Book> findBookById(int id) {
		return bookDao.findById(id);
	}

	public List<Book> findBookByName(String name) {
		return bookDao.findByBookName(name);
	}

	public List<BookCategoryAuthorDto> getAllBookDto() {
		return bookDao.findAllBookDto();
	}

	@Transactional
	public Object saveBook(Book book) {
		if(validation.isNameValid(book.getBookName())) {
			return bookDao.save(book);
		}
		return null;
	}

	@Transactional
	public void deleteBookById(int id) {
		bookDao.deleteById(id);
	}
}
