package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.athira.demo.common.Validation;
import com.athira.demo.dao.ICategoryDao;
import com.athira.demo.entity.Category;

@Transactional
@Service
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryDao categoryDao;
	
	@Autowired
	private Validation validation;

	public List<Category> getAllCategory() {
		return categoryDao.findAll();
	}

	public Category getCategoryById(Integer id) {
		return categoryDao.findById(id).orElseThrow(() -> new RuntimeException("Category not found :" + id));
	}

	public Category saveCategory(Category category) {
		if(validation.isNameValid(category.getCategoryName())) {
			try {
				return categoryDao.save(category);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}catch (DataAccessException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * public Category editCategory(Category category) { Optional<Category>
	 * caOptional = categoryDao.findById(category.getCategoryId()); if
	 * (!caOptional.isPresent()) { throw new Error("Category not found!"); }
	 * Category categoryEntity = caOptional.get();
	 * categoryEntity.setCategoryName(category.getCategoryName()); return
	 * categoryDao.save(category); }
	 */
	public List<Category> getCategoryByName(String name) {
		return categoryDao.findByCategoryName(name);
	}

	public void deleteCategory(Integer id) {
		categoryDao.deleteById(id);
	}
}
