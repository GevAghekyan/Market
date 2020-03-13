package com.example.demo6.services;

import com.example.demo6.exceptions.CategoryNotFoundException;
import com.example.demo6.persistence.model.Category;
import com.example.demo6.persistence.repository.CategoryRepository;
import com.example.demo6.persistence.repository.ProductRepository;
import com.example.demo6.rest.model.category.CategoryRequestModel;
import com.example.demo6.rest.model.category.CategoryResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public CategoryResponseModel createCategory(CategoryRequestModel requestModel) {
        LOGGER.info("Requested to create - {}", requestModel);
        Category category = buildCategoryFromRequest(requestModel);
        Category savedCategory = categoryRepository.save(category);
        LOGGER.info("Successfully created - {}", requestModel);
        return buildCategoryResponseFrom(savedCategory);
    }

    public List<CategoryResponseModel> getAllCategories() {
        LOGGER.info("Requested to get all categories ");
        List<Category> all = categoryRepository.findAll();
        List<CategoryResponseModel> categoryResponseModels = all.stream()
                .map(each -> buildCategoryResponseFrom(each)).collect(Collectors.toList());
        return categoryResponseModels;
    }

    public CategoryResponseModel getCategoryById(Long id) {
        LOGGER.info("Requested to get category by id - {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category not found for id - {}%d", id)));
        return buildCategoryResponseFrom(category);
    }

    public CategoryResponseModel updateCategoryById(Long id, CategoryRequestModel requestModel) {
        LOGGER.info("Requested to update category with id - {} requestModel - {}", id, requestModel);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category not found for id - {}%d", id)));
        Category update = buildCategoryFromRequest(requestModel);
        category.setName(update.getName());
        return buildCategoryResponseFrom(category);
    }

    public void deleteCategoryById(Long id) {
        LOGGER.info("Requested to delete category with id - {}", id);
        categoryRepository.deleteById(id);
        LOGGER.info("Category is deleted");
    }

    private Category buildCategoryFromRequest(CategoryRequestModel requestModel) {
        Category category = new Category();
        category.setName(requestModel.getName());
        return category;
    }

    private CategoryResponseModel buildCategoryResponseFrom(Category category) {
        CategoryResponseModel responseModel = new CategoryResponseModel();
        responseModel.setId(category.getId());
        responseModel.setName(category.getName());
        responseModel.setQuantity(productRepository.countByCategory_Name(category.getName()));
        return responseModel;
    }
}
