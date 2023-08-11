package com.example.project3_rehamalmutairi.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckIds {
    private final MerchantService merchantService;
    private final ProductService productService;

    public boolean isFound(Integer productid , Integer merchantid){
        boolean foundProduct = false;
        boolean foundMerchant  = false;

        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productid){
                foundProduct = true;
            }
        }

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merchantid)
                foundMerchant = true;
        }

        if (foundProduct && foundMerchant)
            return true;
        else
            return false;
    }


}
