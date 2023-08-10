package com.example.project3_rehamalmutairi.Controller;

import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.Category;
import com.example.project3_rehamalmutairi.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getAllCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addNewCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        categoryService.addNewCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("New category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id , @RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isValied = categoryService.updateCategory(id , category);

        if(isValied)
            return ResponseEntity.status(200).body(new ApiResponse("The category successfully updated"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the category id is wrong"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        boolean isDeleted = categoryService.deleteCategory(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("The category successfully deleted"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, you entered wrong category id"));
    }

}
