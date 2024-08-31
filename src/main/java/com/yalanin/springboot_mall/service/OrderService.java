package com.yalanin.springboot_mall.service;

import com.yalanin.springboot_mall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
