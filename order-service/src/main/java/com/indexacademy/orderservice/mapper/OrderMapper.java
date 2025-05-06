package com.indexacademy.orderservice.mapper;

import com.indexacademy.orderservice.dto.OrderLineItemsDto;
import com.indexacademy.orderservice.dto.OrderRequest;
import com.indexacademy.orderservice.dto.OrderResponse;
import com.indexacademy.orderservice.model.Order;
import com.indexacademy.orderservice.model.OrderLineItems;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderNumber", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "orderLineItemsList", source = "orderLineItemsDtoList")
    Order toEntity(OrderRequest orderRequest);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderLineItems toOrderLineItems(OrderLineItemsDto orderLineItemsDto);

    @Mapping(target = "orderLineItemsList", source = "orderLineItemsList")
    OrderResponse toOrderResponse(Order order);

    OrderLineItemsDto toOrderLineItemsDto(OrderLineItems orderLineItems);
}
