package com.sharkb.product.service.product;

import com.sharkb.product.dto.ProductCreateDto;
import com.sharkb.product.dto.ProductDto;
import com.sharkb.product.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(String id);

    void addProduct(ProductCreateDto productCreateDto);

    ProductDto updateProduct(String id, ProductUpdateDto productUpdateDto);

    void deleteProduct(String id);
}
