package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer> {

	@Query("from Category WHERE categoryName LIKE %?1%")
	List<Category> findByCategoryName(String name);

}
