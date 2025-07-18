package com.sharkb.product.service.product;

import com.sharkb.product.dto.ProductCreateDto;
import com.sharkb.product.dto.ProductDto;
import com.sharkb.product.dto.ProductUpdateDto;
import com.sharkb.product.mapper.ProductMapper;
import com.sharkb.product.model.Product;
import com.sharkb.product.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDtos(productsRepository.findAll());
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product =
                productsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public void addProduct(ProductCreateDto productCreateDto) {
        Product product = productMapper.toEntity(productCreateDto);
        productsRepository.save(product);
        log.info("Product with id {} was added", product.getId());
    }

    @Override
    public ProductDto updateProduct(String id, ProductUpdateDto productUpdateDto) {
        Product product = productsRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product not found")
        );
        if (productUpdateDto.name() != null) {
            product.setName(productUpdateDto.name());
        }
        if (productUpdateDto.description() != null) {
            product.setDescription(productUpdateDto.description());
        }
        if (productUpdateDto.price() != null) {
            product.setPrice(productUpdateDto.price());
        }
        if (productUpdateDto.quantity() != null) {
            product.setQuantity(productUpdateDto.quantity());
        }
        if (productUpdateDto.tags() != null) {
            product.setTags(productUpdateDto.tags());
        }

        productsRepository.save(product);
        log.info("Product with id {} was updated", product.getId());

        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(String id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            log.info("Product with id {} was deleted", id);
        } else {
            log.error("Product with id {} not found", id);
            throw new NoSuchElementException("Product not found");
        }
    }
}
