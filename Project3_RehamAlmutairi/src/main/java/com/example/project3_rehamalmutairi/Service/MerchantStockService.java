package com.example.project3_rehamalmutairi.Service;

import com.example.project3_rehamalmutairi.Model.Merchant;
import com.example.project3_rehamalmutairi.Model.MerchantStock;
import com.example.project3_rehamalmutairi.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock>merchantStocks = new ArrayList<>();
    private final ProductService product;
    private final MerchantService merchant;
    public ArrayList<MerchantStock> getAllMerchantStocks(){
        return merchantStocks;
    }

    public boolean isFound_Ids(Integer productId, Integer merchantId){

        boolean foundProduct = false;
        boolean foundMerchant = false;

        for (int i = 0; i < product.products.size(); i++) {
            if(product.products.get(i).getId() == productId)
                foundProduct = true;
        }
        for (int i = 0; i < merchant.merchants.size(); i++) {
            if (merchant.merchants.get(i).getId() == merchantId)
                foundMerchant = true;
        }
        if(foundMerchant && foundProduct)
            return true;
        else
            return false;
    }



    public boolean addMerchantStock(MerchantStock merchantStock){
        if(isFound_Ids(merchantStock.getProductid(), merchantStock.getMerchantid())) {
            merchantStocks.add(merchantStock);
            return true;
        }
        else
            return false;

    }

    public boolean updateMerchantStock(Integer id , MerchantStock merchantStock){

        for (int i = 0; i < merchantStocks.size(); i++) {
            if ((merchantStocks.get(i).getId() == id) && (isFound_Ids(merchantStock.getProductid(),merchantStock.getMerchantid())) ){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(Integer id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
}
