package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Author;

@Repository
public interface IAuthorDao extends JpaRepository<Author, Integer> {

	@Query("from Author WHERE authorName LIKE %?1%")
	List<Author> findByAuthorName(String name);

}
