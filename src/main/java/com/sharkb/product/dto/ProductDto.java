package com.sharkb.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductDto(
        String id,
        String name,
        String description,
        String category,
        BigDecimal price,
        List<String> tags,
        LocalDateTime createdAt) {
}
