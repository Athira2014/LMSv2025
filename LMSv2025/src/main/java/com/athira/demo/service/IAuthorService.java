package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Author;

public interface IAuthorService {

	List<Author> getAllAuthors();

	Optional<Author> getAuthorById(Integer id);

	Author saveAuthor(Author author);

	Author editAuthor(Author author);

	List<Author> findAthorByName(String name);

	void deleteAuthorById(int id);

}
