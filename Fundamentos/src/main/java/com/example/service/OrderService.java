package com.example.service;

import com.example.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    Order getOrderById(Long id);
    Order updateOrderStatus(Long id);
    List<Order> getOrdersByRestaurantAndStatus(Long restaurantId, Long statusId);
    List<Order> getOrdersByRestaurant(Long id);
}
