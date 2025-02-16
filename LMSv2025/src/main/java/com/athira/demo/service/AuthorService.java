package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.common.Validation;
import com.athira.demo.dao.IAuthorDao;
import com.athira.demo.entity.Author;

@Service
public class AuthorService implements IAuthorService {

	@Autowired
	IAuthorDao authorDao;
	
	@Autowired
	private Validation validation;

	@Transactional
	public List<Author> getAllAuthors() {
		return authorDao.findAll();
	}

	@Transactional
	public Optional<Author> getAuthorById(Integer id) {
		return authorDao.findById(id);
	}

	@Transactional
	public Author saveAuthor(Author author) {
		if(validation.isNameValid(author.getAuthorName())) {
			return authorDao.save(author);
		}
		return null;
	}

	@Transactional
	public Author editAuthor(Author author) {
		
		Optional<Author> auOptional = authorDao.findById(author.getAuthorId());
		if (!auOptional.isPresent()) {
			throw new Error("Author not found!");
		}
		Author authorEntity = auOptional.get();
		authorEntity.setAuthorName(author.getAuthorName());
		return authorDao.save(authorEntity);
		
	}

	public List<Author> findAthorByName(String name) {
		return authorDao.findByAuthorName(name);
	}

	@Transactional
	public void deleteAuthorById(int id) {
		authorDao.deleteById(id);
	}

}
