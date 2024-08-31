package com.yalanin.springboot_mall.service;

import com.yalanin.springboot_mall.dto.CreateOrderRequest;
import com.yalanin.springboot_mall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
