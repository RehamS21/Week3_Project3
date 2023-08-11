package com.example.project3_rehamalmutairi.Service;

import com.example.project3_rehamalmutairi.Model.Merchant;
import com.example.project3_rehamalmutairi.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantStockService merchantStockService;
    private final  ProductService productService;
    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAllMerchant(){
        return merchants;
    }

    public void addNewMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public boolean updateMerchant(Integer id, Merchant merchant){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(Integer id){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean check_Ids(Integer productid, Integer merchantId){
        boolean found_product = false;
        boolean found_merchant = false;

        for (int i = 0; i < productService.products.size(); i++) {
            if(productService.products.get(i).getId() == productid)
                found_product = true;
        }

        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId() == merchantId)
                found_merchant = true;
        }

        if (found_product && found_merchant)
            return true;
        else
            return false;

    }

    public boolean additionalStock(Integer productid,Integer merchantId,Integer additionalStock){
        boolean isFound = check_Ids(productid,merchantId);
//        boolean isAddStock = false;

        if (isFound) {
            for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
                if (productid == merchantStockService.merchantStocks.get(i).getProductid() && merchantId == merchantStockService.merchantStocks.get(i).getMerchantid() ){
                    merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() + additionalStock);
//                    MerchantStock merchantStock = merchantStockService.merchantStocks.get(i);
//                    Integer addStock = merchantStock.getStock() + additionalStock;
//                    merchantStock.setStock(addStock);
                    return true;
                }
            }
        }

        return false;
    }
}
