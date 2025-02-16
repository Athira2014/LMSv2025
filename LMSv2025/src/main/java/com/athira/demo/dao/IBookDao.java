package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.athira.demo.dto.BookCategoryAuthorDto;
import com.athira.demo.entity.Book;

@Repository
public interface IBookDao extends JpaRepository<Book, Integer> {

	//Custom JPQL
	
	@Query("from Book WHERE bookName LIKE %?1%")
	List<Book> findByBookName(String name);


	@Query("SELECT new com.athira.demo.dto.BookCategoryAuthorDto(b.bookId, b.bookName, c.categoryName ,a.authorName)"
			+ "from Book b INNER JOIN b.category c INNER JOIN b.author a ORDER BY b.bookId")
	List<BookCategoryAuthorDto> findAllBookDto();
	
}
