package com.yalanin.springboot_mall.dao;

import com.yalanin.springboot_mall.dto.ProductQueryParams;
import com.yalanin.springboot_mall.dto.ProductRequest;
import com.yalanin.springboot_mall.model.Product;

import java.util.List;
public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    Integer countProduct(ProductQueryParams productQueryParams);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    void updateStock(Integer productId, Integer stock);
}
