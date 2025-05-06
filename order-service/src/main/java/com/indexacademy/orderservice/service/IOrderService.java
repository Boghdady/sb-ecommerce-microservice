package com.indexacademy.orderservice.service;

import com.indexacademy.orderservice.dto.OrderRequest;
import com.indexacademy.orderservice.dto.OrderResponse;

import java.util.List;

public interface IOrderService {
    void placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);
}
