package com.example.project3_rehamalmutairi.Service;


import com.example.project3_rehamalmutairi.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAllCategories(){
        return categories;
    }

    public void addNewCategory(Category category){
        categories.add(category);
    }

    public boolean updateCategory(Integer id, Category category){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId() == id){
                categories.set(i , category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(Integer id){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId() == id){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }
}
