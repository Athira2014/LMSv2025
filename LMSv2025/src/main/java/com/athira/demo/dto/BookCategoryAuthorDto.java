package com.athira.demo.dto;

public class BookCategoryAuthorDto {
	
	private Integer bookId;
	private String bookName;
	private String categoryName;
	private String authorName;
	
	public BookCategoryAuthorDto() {
	}

	public BookCategoryAuthorDto(Integer bookId, String bookName, String categoryName, String authorName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.categoryName = categoryName;
		this.authorName = authorName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
