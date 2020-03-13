package com.example.demo6.rest.controller;

import com.example.demo6.rest.model.category.CategoryRequestModel;
import com.example.demo6.rest.model.category.CategoryResponseModel;
import com.example.demo6.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<CategoryResponseModel> createCategory(@RequestBody CategoryRequestModel requestModel) {
        LOGGER.info("Creating category - {}", requestModel);
        CategoryResponseModel responseModel = categoryService.createCategory(requestModel);
        return ResponseEntity.ok(responseModel);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoryResponseModel>> getAll() {
        LOGGER.info("Getting all categories");
        List<CategoryResponseModel> categoryResponseModels = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryResponseModels);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Getting category by id -{}", id);
        CategoryResponseModel categoryResponseModel = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponseModel);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponseModel> updateById(@PathVariable Long id, @RequestBody CategoryRequestModel requestModel) {
        LOGGER.info("Updating category by - {}", id);
        CategoryResponseModel categoryResponseModel = categoryService.updateCategoryById(id, requestModel);
        return ResponseEntity.ok(categoryResponseModel);
    }

    @DeleteMapping(value = "/category/{id}")
    public void deleteById(@PathVariable Long id) {
        LOGGER.info("Deleting category by - {}", id);
        categoryService.deleteCategoryById(id);
    }
}
