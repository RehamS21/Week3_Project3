package com.example.project3_rehamalmutairi.Controller;

import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.MerchantStock;
import com.example.project3_rehamalmutairi.Service.CheckIds;
import com.example.project3_rehamalmutairi.Service.MerchantService;
import com.example.project3_rehamalmutairi.Service.MerchantStockService;
import com.example.project3_rehamalmutairi.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;
    private final CheckIds checkIds;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean checkValid = checkIds.isFound(merchantStock.getProductid(),merchantStock.getMerchantid());


        if (checkValid) {
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body(new ApiResponse("added new merchant stock successfully"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, you add merchant id or product id wrong and don't match exist id"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isvalied = merchantStockService.updateMerchantStock(id, merchantStock);
        boolean isChecked = checkIds.isFound(merchantStock.getProductid() , merchantStock.getMerchantid());
        if (isvalied && isChecked)
            return ResponseEntity.status(200).body(new ApiResponse("updated new merchant stock successfully"));
        else
//            return ResponseEntity.status(400).body(new ApiResponse("Sorry, merchant stock id is wrong "));
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, merchant stock id is wrong or add wrong product id and merchant id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock deleted successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, wrong merchant stock id"));
    }
}
