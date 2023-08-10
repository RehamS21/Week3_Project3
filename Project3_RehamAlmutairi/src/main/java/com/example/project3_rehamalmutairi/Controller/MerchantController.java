package com.example.project3_rehamalmutairi.Controller;

import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.Merchant;
import com.example.project3_rehamalmutairi.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant(){
        return ResponseEntity.status(200).body(merchantService.getAllMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addNewMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        merchantService.addNewMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("add new merchant successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id , @RequestBody @Valid Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isvalied = merchantService.updateMerchant(id,merchant);

        if (isvalied)
            return ResponseEntity.status(200).body(new ApiResponse("The merchant information updated successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the merchant id is wrong"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){
        boolean isDeleted = merchantService.deleteMerchant(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("The merchant deleted successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the merchant id is wrong"));
    }

}