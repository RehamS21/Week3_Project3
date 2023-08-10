package com.example.project3_rehamalmutairi.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "merchant stock id should not null value")
    @Positive(message = "The id of merchant stock must be positive and greater than zero")
    private Integer id;

    @NotNull(message = "product id should not a null")
    @Positive(message = "The product id of merchant stock must be positive and greater than zero")
    private Integer productid;

    @NotNull(message = "merchant id should not a null")
    @Positive(message = "The merchant id of merchant stock must be positive and greater than zero")
    private Integer merchantid;

    @NotEmpty( message = "stock should not a null")
    @Size(min = 11 , message = "The length of stock should be more than 10")
    private String stock;
}
