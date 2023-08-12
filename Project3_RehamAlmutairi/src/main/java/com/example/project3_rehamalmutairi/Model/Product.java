package com.example.project3_rehamalmutairi.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.SecureRandom;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "Product id must not null")
    @Positive(message = "Product id must a positive value and not equal to zero")
    private Integer id;

    @NotEmpty(message = "Product name must not empty")
    @Size(min = 4, message = "The length of product name must more than 3")
    private String name;

    @NotNull(message = "The price should not have a null value")
    @Positive(message = "The price should be positive number and not equal to zero")
    private Double price;

    @NotNull(message = "Category should not empty")
    @Positive(message = "Category id must a positive number")
    private Integer categoryid;

}
