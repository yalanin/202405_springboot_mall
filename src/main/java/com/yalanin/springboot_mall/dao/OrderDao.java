package com.yalanin.springboot_mall.dao;

import com.yalanin.springboot_mall.dto.OrderQueryParams;
import com.yalanin.springboot_mall.model.Order;
import com.yalanin.springboot_mall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);

    Integer countOrder(OrderQueryParams orderQueryParams);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);
}
