package com.athira.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BookId", nullable = false)
	private Integer bookId;
	
	@Column(name = "BookName")
	private String bookName;
	
	@ManyToOne
	@JoinColumn(name = "CategoryId",  insertable = false, updatable = false)
    private Category category;
	
	@Column(name = "CategoryId")
	private Integer categoryId;
    
    @ManyToOne
    @JoinColumn(name = "AuthorId" , insertable = false, updatable = false)
    private Author author;
    
	@Column(name = "AuthorId")
	private Integer authorId;
	
    @Column(name = "AvailableCopies", nullable = false)
    private Integer availableCopies;
    
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<BorrowTransaction> borrows;
    
	public Book(Integer bookId, String bookName, Category category, Author author, Integer availableCopies) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.category = category;
		this.author = author;
		this.availableCopies = availableCopies;
	}

	public Book() {
	}

	public Integer getBookId() {
		return bookId;
	}
    public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer available) {
			this.availableCopies = available;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public List<BorrowTransaction> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<BorrowTransaction> borrows) {
		this.borrows = borrows;
	}
    
}
