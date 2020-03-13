package com.example.demo6.services;

import com.example.demo6.exceptions.CategoryNotFoundException;
import com.example.demo6.exceptions.ProductNotFoundException;
import com.example.demo6.persistence.model.Category;
import com.example.demo6.persistence.model.Product;
import com.example.demo6.persistence.repository.CategoryRepository;
import com.example.demo6.persistence.repository.ProductRepository;
import com.example.demo6.rest.model.product.ProductRequestModel;
import com.example.demo6.rest.model.product.ProductResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseModel createProduct(ProductRequestModel requestModel) {
        LOGGER.info("Creating product - {}", requestModel);
        Product product = buildProductFromRequest(requestModel);
        productRepository.save(product);
        LOGGER.info("Product saved - {}", requestModel);
        return buildResponseFrom(product);
    }

    public List<ProductResponseModel> getAll() {
        LOGGER.info("Getting all products");
        List<ProductResponseModel> all = productRepository.findAll().stream()
                .map(product -> buildResponseFrom(product))
                .collect(Collectors.toList());
        return all;
    }

    public ProductResponseModel findById(Long id) {
        LOGGER.info("Getting product by id - {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found for id - {}", id)));
        return buildResponseFrom(product);
    }

    public ProductResponseModel updateById(Long id, ProductRequestModel requestModel) {
        LOGGER.info("Updating product by id - {} product - {}", id, requestModel);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found for id - {}", id)));
        Product updated = buildProductFromRequest(requestModel);
        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setCategory(updated.getCategory());
        productRepository.save(product);
        LOGGER.info("Product updated - {}", product);
        return buildResponseFrom(product);
    }

    public void deleteByID(Long id) {
        LOGGER.info("Deleting by id - {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Product is deleted");
    }

    private Product buildProductFromRequest(ProductRequestModel requestModel) {
        Product product = new Product();
        product.setName(requestModel.getName());
        product.setPrice(requestModel.getPrice());
        Category category = categoryRepository.findById(requestModel.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category not found for id - {}", requestModel.getCategoryId())));
        product.setCategory(category);
        return product;
    }

    private ProductResponseModel buildResponseFrom(Product product) {
        ProductResponseModel responseModel = new ProductResponseModel();
        responseModel.setId(product.getId());
        responseModel.setName(product.getName());
        responseModel.setPrice(product.getPrice());
        responseModel.setCategoryName(product.getCategory().getName());
        return responseModel;
    }
}
