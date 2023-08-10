package com.example.project3_rehamalmutairi.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotNull(message = "merchant id must not null value")
    @Positive(message = "Merchant id must be positive value and greater than zero")
    private Integer id;

    @NotEmpty(message = "merchant name should not a null value")
    @Size(min = 4, message = "The length of merchant name should be more than 3")
    private String name;
}
