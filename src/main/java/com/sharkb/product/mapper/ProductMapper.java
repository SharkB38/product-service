package com.sharkb.product.mapper;

import com.sharkb.product.dto.ProductCreateDto;
import com.sharkb.product.dto.ProductDto;
import com.sharkb.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductCreateDto productCreateDto);

    List<ProductDto> toDtos(List<Product> products);
}
