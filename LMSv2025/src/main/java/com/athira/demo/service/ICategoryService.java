package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Category;

public interface ICategoryService {

	List<Category> getAllCategory();

	Category getCategoryById(Integer id);

	Category saveCategory(Category category);

	//Category editCategory(Category category);

	List<Category> getCategoryByName(String name);

	void deleteCategory(Integer id);

}
