package com.sharkb.product.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "description")
    private String description;

    @Field(name = "category")
    private String category;

    @Field(name = "price")
    private BigDecimal price;

    @Field(name = "quantity")
    private Integer quantity;

    @Field(name = "tags")
    private List<String> tags;

    @CreatedDate
    @Field(name = "created_at")
    private LocalDateTime createdAt;
}
