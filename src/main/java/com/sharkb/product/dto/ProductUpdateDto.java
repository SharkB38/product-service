package com.sharkb.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateDto(
        String name,
        String description,
        String category,
        BigDecimal price,
        Integer quantity,
        List<String> tags) {
}
