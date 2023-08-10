package com.example.project3_rehamalmutairi.Service;

import com.example.project3_rehamalmutairi.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
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
}
