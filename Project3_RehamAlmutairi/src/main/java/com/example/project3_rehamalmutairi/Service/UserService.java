package com.example.project3_rehamalmutairi.Service;


import com.example.project3_rehamalmutairi.Model.Product;
import com.example.project3_rehamalmutairi.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public void addNewUser(User user) {
        users.add(user);
    }

    public boolean updateUser(Integer id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(Integer id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkValid(Integer userid,Integer productid , Integer merhcantid){
        boolean foundUserid= false;
        boolean foundProduct = false;
        boolean foundMerchantid = false;

        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productid) {
                foundProduct = true;
                break;
            }
        }
        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merhcantid) {
                foundMerchantid = true;
                break;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userid) {
                foundUserid = true;
                break;
            }
        }

        if((foundProduct && foundMerchantid) && foundUserid)
            return true;
        else
            return false;

    }

    public boolean merchantInStock(Integer productId){
        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getProductid() == productId)
                return true;
        }
        return false;
    }

    public Double checkBalance(Integer productid , Integer userId){
        Double balance = 0.0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == userId){
                balance = checkBalance(productid,users.get(i));
            }
        }
        return balance;
    }

    public Double checkBalance(Integer product, User user){
        Double reducedBalance = 0.0;
        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == product){
                 reducedBalance = user.getBalance() - productService.products.get(i).getPrice();
                 return reducedBalance;
            }
        }
        return reducedBalance;
    }

    public boolean buyProducts(Integer userid,Integer productid , Integer merhcantid){
        Double reduceBalance = 0.0;
        boolean isCompletelyBuy = false;
        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {

            if((merchantStockService.merchantStocks.get(i).getProductid() == productid) && (merchantStockService.merchantStocks.get(i).getMerchantid() == merhcantid)){
                int reduceStock = merchantStockService.merchantStocks.get(i).getStock() - 1;
                merchantStockService.merchantStocks.get(i).setStock(reduceStock);
                isCompletelyBuy = true;
            }
                }
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getId() == userid){
                    reduceBalance = checkBalance(productid,users.get(i));
                    if(reduceBalance > 0){
                        users.get(i).setBalance(reduceBalance);
                        isCompletelyBuy = true;
                    }else {
                        isCompletelyBuy = false;
                    }
                }
            }
        return isCompletelyBuy;
    }

    //for extra credit
    public void scoreRating(Integer merhantid, Integer scoreRating){
        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merhantid){
                merchantService.merchants.get(i).setScoreRating(scoreRating);
            }
        }
    }

    public boolean checkValid(Integer merhcantid){

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merhcantid) {
                return true;
            }
        }

        return false;
    }

    // Range of score rate
    public boolean checkScoreRate(Integer scoreRate){
        if(scoreRate >= 1 && scoreRate <=5){
            return true;
        }else
            return false;
    }

}
