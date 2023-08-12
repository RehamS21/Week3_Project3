package com.example.project3_rehamalmutairi.Service;


import com.example.project3_rehamalmutairi.Model.Category;
import com.example.project3_rehamalmutairi.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService category;
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public boolean isFoundCategoryId(Integer product_CategoryId){
        for (int i = 0; i < category.categories.size(); i++) {
            if( category.categories.get(i).getId() == product_CategoryId)
                return true;
        }
        return false;

    }

    public boolean addNewProduct(Product product){
        if (isFoundCategoryId(product.getCategoryid())) {
            boolean isChecked = checkIds(product.getId());
            if (isChecked) {
                products.add(product);
                return true;
            }else
                return false;
        }else
            return false;
    }
    public boolean checkIds(Integer id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id)
                return false;
        }
        return true;
    }

    public boolean updateProduct(Integer id , Product product){
        boolean isChecked = checkIds(id,product);
        if (isChecked) {
            for (int i = 0; i < products.size(); i++) {
                if ((products.get(i).getId() == id) && isFoundCategoryId(product.getCategoryid())) {
                    products.set(i, product);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkIds(Integer id, Product product){
        for (int i = 0; i < products.size(); i++) {
            if(product.getId() != id && product.getId() == products.get(i).getId())
                return false;
        }
        return true;
    }

    public boolean deleteProduct(Integer id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

}
