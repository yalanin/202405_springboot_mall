package com.yalanin.springboot_mall.service;

import com.yalanin.springboot_mall.dto.ProductRequest;
import com.yalanin.springboot_mall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProductById(Integer ProductId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
