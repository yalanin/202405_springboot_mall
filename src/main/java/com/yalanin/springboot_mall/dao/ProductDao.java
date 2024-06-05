package com.yalanin.springboot_mall.dao;

import com.yalanin.springboot_mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
