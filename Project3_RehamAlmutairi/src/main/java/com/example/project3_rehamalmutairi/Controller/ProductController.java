package com.example.project3_rehamalmutairi.Controller;


import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.Product;
import com.example.project3_rehamalmutairi.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getAllprodcuts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addNewProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        productService.addNewProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("The product : '"+product.getName()+"' added successfully"));
    }


}
