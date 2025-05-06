package com.indexacademy.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
