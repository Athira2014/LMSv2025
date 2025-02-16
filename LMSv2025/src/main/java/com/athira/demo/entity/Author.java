package com.athira.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AuthorId", nullable = false)
	private Integer authorId;
	
	@Column(name = "AuthorName", nullable = false, length = 20)
    private String authorName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private List<Book> books;

	public Author() {
	}

	public Author(Integer authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		if (authorName == null || authorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (authorName.length() > 20) {
			throw new IllegalArgumentException("Name cannot exceed 20 characters.");
		} 
		this.authorName = authorName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
