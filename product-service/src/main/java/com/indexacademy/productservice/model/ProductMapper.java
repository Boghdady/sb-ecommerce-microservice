package com.indexacademy.productservice.model;

import com.indexacademy.productservice.dto.ProductRequest;
import com.indexacademy.productservice.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    
    Product toEntity(ProductRequest productRequest);
    
    ProductResponse toDto(Product product);

    void updateProductFromDto(ProductRequest productRequest, @MappingTarget Product product);
}
