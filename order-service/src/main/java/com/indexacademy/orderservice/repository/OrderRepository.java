package com.indexacademy.orderservice.repository;

import com.indexacademy.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderLineItemsList WHERE o.id = :id")
    Optional<Order> findByIdWithOrderLines(@Param("id") Long id);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderLineItemsList")
    List<Order> findAllWithOrderLines();
}
