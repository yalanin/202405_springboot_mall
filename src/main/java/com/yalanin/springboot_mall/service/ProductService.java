package com.yalanin.springboot_mall.service;

import com.yalanin.springboot_mall.dto.ProductQueryParams;
import com.yalanin.springboot_mall.dto.ProductRequest;
import com.yalanin.springboot_mall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer ProductId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
