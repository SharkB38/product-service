package com.sharkb.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreateDto(
        String name,
        String description,
        String category,
        BigDecimal price,
        List<String> tags) {
}
