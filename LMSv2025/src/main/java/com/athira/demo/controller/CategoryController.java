package com.athira.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Category;
import com.athira.demo.service.ICategoryService;

@RestController
@RequestMapping("api/")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@Autowired
	private APIResponse apiResponse;
	
	@GetMapping("categories")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

	@GetMapping("categories/{id}")
	public ResponseEntity<APIResponse> getCategoryById(@PathVariable Integer id) {
		try {
			Category category = categoryService.getCategoryById(id);
			apiResponse.setStatus(200);
			apiResponse.setData(category);
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("categories/search/{name}")
	public List<Category> getCategoryByName(@PathVariable String name) {
		return categoryService.getCategoryByName(name);
	}

	@PostMapping("categories")
	public ResponseEntity<APIResponse> saveCategory(@RequestBody Category category) {
		if(categoryService.saveCategory(category) == null) {
			apiResponse.setStatus(500);
			apiResponse.setData("Category should contain only alphabets");
			apiResponse.setError("Category Error");
		}
		apiResponse.setStatus(200);
		apiResponse.setData("Category created succesfully.");
		apiResponse.setError(null);
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PutMapping("categories")
	public ResponseEntity<APIResponse> editCategory(@RequestBody Category category) {
		if(categoryService.saveCategory(category) == null) {
			apiResponse.setStatus(500);
			apiResponse.setData("Category should contain only alphabets");
			apiResponse.setError("Category Error");
		}
		apiResponse.setStatus(200);
		apiResponse.setData("Category updated succesfully.");
		apiResponse.setError(null);
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@DeleteMapping("categories/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
	}
}
