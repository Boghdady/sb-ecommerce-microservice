package com.indexacademy.orderservice.service;

import com.indexacademy.orderservice.dto.OrderRequest;
import com.indexacademy.orderservice.dto.OrderResponse;
import com.indexacademy.orderservice.mapper.OrderMapper;
import com.indexacademy.orderservice.model.Order;
import com.indexacademy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());

        // Set the order reference in each OrderLineItem
        order.getOrderLineItemsList().forEach(item -> item.setOrder(order));

        // Calculate total amount
        BigDecimal totalAmount = order.getOrderLineItemsList().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAllWithOrderLines().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toOrderResponse(
                orderRepository.findByIdWithOrderLines(id)
                        .orElseThrow(() -> new RuntimeException("Order not found with id: " + id)
        ));
    }
}
