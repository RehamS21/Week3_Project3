package com.example.project3_rehamalmutairi.Service;


import com.example.project3_rehamalmutairi.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public void addNewProduct(Product product){
        products.add(product);
    }

}
