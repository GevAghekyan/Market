package com.example.demo6.rest.controller;

import com.example.demo6.rest.model.product.ProductRequestModel;
import com.example.demo6.rest.model.product.ProductResponseModel;
import com.example.demo6.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductResponseModel> createProduct(@RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Creating product - {}", requestModel);
        ProductResponseModel productResponseModel = productService.createProduct(requestModel);
        return ResponseEntity.ok(productResponseModel);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductResponseModel>> getAll() {
        LOGGER.info("Getting all products");
        List<ProductResponseModel> all = productService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Getting product by id -{}", id);
        ProductResponseModel productResponseModel = productService.findById(id);
        return ResponseEntity.ok(productResponseModel);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> updateById(@PathVariable Long id, @RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Updating product by - {}", id);
        ProductResponseModel responseModel = productService.updateById(id, requestModel);
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping(value = "/product/{id}")
    public void deleteById(@PathVariable Long id) {
        LOGGER.info("Deleting product by - {}", id);
        productService.deleteByID(id);
    }
}
