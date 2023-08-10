package com.example.project3_rehamalmutairi.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "Category id should not have a null value")
    @Positive(message = "Category id must have a positive numbers and not equal to zero")
    private Integer id;

    @NotEmpty(message = "name should noy have empty")
    @Size(min = 4 , message = "name must have more than 3 length")
    private String name;

}
