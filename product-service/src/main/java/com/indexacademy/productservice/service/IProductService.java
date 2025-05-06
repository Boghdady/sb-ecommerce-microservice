package com.indexacademy.productservice.service;

import com.indexacademy.productservice.dto.ProductRequest;
import com.indexacademy.productservice.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
