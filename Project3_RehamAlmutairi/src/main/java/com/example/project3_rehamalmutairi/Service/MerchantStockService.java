package com.example.project3_rehamalmutairi.Service;

import com.example.project3_rehamalmutairi.Model.Merchant;
import com.example.project3_rehamalmutairi.Model.MerchantStock;
import com.example.project3_rehamalmutairi.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock>merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getAllMerchantStocks(){
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock){
        boolean isChecked = checkIds(merchantStock.getId());
        if (isChecked) {
            merchantStocks.add(merchantStock);
            return true;
        }else
            return false;
    }
    public boolean checkIds(Integer id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id)
                return false;
        }
        return true;
    }


    public boolean updateMerchantStock(Integer id , MerchantStock merchantStock){
        boolean isChecked = checkIds(id,merchantStock);
        if (isChecked) {
            for (int i = 0; i < merchantStocks.size(); i++) {
                if ((merchantStocks.get(i).getId() == id)) {
                    merchantStocks.set(i, merchantStock);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIds(Integer id, MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStock.getId() != id && merchantStock.getId() == merchantStocks.get(i).getId())
                return false;
        }
        return true;
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
