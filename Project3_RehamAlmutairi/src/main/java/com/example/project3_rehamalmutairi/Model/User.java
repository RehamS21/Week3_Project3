package com.example.project3_rehamalmutairi.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class User {
    @NotNull(message = "user id should not a null value")
    private Integer id;
    @NotEmpty(message = "username should not an empty")
    @Size(min = 5 , message = "The length of username should be more than 5")
    private String username;

    @NotEmpty(message = "password should not an empty")
    @Size(min = 7 , message = "The length of password should be more than 6")
//    @Pattern()
    private String password;
    @NotEmpty(message = "email should not an empty")
//    @Pattern()
    private String email;
    @NotEmpty(message = "role should not an empty")
//    @Pattern()
    private String role;

    @NotNull(message = "balance should not an empty")
    @Positive(message = "Balance should a positive number and not equal to zero")
    private Double balance;

}
