package com.example.project3_rehamalmutairi.Controller;

import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.Merchant;
import com.example.project3_rehamalmutairi.Service.MerchantService;
import com.example.project3_rehamalmutairi.Service.MerchantStockService;
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

        boolean isAdded = merchantService.addNewMerchant(merchant);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("add new merchant successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the merchant id already taken"));
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
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the merchant id is wrong or the updated id already taken"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id){
        boolean isDeleted = merchantService.deleteMerchant(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("The merchant deleted successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the merchant id is wrong"));
    }

    @PutMapping("/addStock/{productId}/{merchantid}/{stock}")
    public ResponseEntity addAdditionalStock(@PathVariable Integer productId, @PathVariable Integer merchantid, @PathVariable Integer stock){
        boolean isAdd = merchantService.additionalStock(productId,merchantid,stock);

        if(isAdd) {
            boolean isChecked = merchantService.checkStockNumber(stock);
            if (isChecked)
                return ResponseEntity.status(200).body(new ApiResponse("Add additional stock successfully"));
            else
                return ResponseEntity.status(400).body(new ApiResponse("invalid stock number, you must enter more than zero"));
        }
        else
            return ResponseEntity.status(400).body(new ApiResponse("Please verify from the product is or merchant id again"));

    }



}
