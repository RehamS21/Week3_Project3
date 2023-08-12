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

    public boolean checkIds(Integer id){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id)
                return false;
        }
        return true;
    }

    public boolean addNewCategory(Category category){
        boolean notSameId = checkIds(category.getId());
        if (notSameId) {
            categories.add(category);
            return true;
        }
        else
            return false;
    }

    public boolean updateCategory(Integer id, Category category){
        boolean checkId = checkIds(id,category);
        if (checkId) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getId() == id) {
                    categories.set(i, category);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIds(Integer id, Category category){
        for (int i = 0; i < categories.size(); i++) {
            if(category.getId() != id && category.getId() == categories.get(i).getId())
                return false;
        }
        return true;
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
