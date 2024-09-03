package com.yalanin.springboot_mall.service.impl;

import com.yalanin.springboot_mall.dao.OrderDao;
import com.yalanin.springboot_mall.dao.ProductDao;
import com.yalanin.springboot_mall.dao.UserDao;
import com.yalanin.springboot_mall.dto.BuyItem;
import com.yalanin.springboot_mall.dto.CreateOrderRequest;
import com.yalanin.springboot_mall.model.Order;
import com.yalanin.springboot_mall.model.OrderItem;
import com.yalanin.springboot_mall.model.Product;
import com.yalanin.springboot_mall.model.User;
import com.yalanin.springboot_mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // 檢查使用者資訊
        User user = userDao.getUserById(userId);

        if(user == null) {
            log.warn("========");
            log.warn("userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            if(product == null) {
                log.warn("========");
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("========");
                log.warn("商品 {} 庫存不足，欲購數量：{}，剩餘數量：{}",
                        buyItem.getProductId(), buyItem.getQuantity(), product.getStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            // 計算訂單總金額
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // 轉換訂單細項
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        // 創建訂單細項
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return  order;
    }
}
