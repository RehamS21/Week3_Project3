package com.example.project3_rehamalmutairi.Controller;

import com.example.project3_rehamalmutairi.ApiResponse.ApiResponse;
import com.example.project3_rehamalmutairi.Model.MerchantStock;
import com.example.project3_rehamalmutairi.Model.User;
import com.example.project3_rehamalmutairi.Service.MerchantStockService;
import com.example.project3_rehamalmutairi.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        userService.addNewUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean isValid = userService.updateUser(id,user);

        if (isValid)
            return ResponseEntity.status(200).body(new ApiResponse("The user information updated successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the user id is wrong"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("The user deleted successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry , the user id is wrong"));
    }

    @PutMapping("/buyProduct/{userid}/{productid}/{merchantid}")
    public ResponseEntity buProducts(@PathVariable Integer userid,@PathVariable Integer productid, @PathVariable Integer merchantid){
        boolean isVaid = userService.checkValid(userid, productid, merchantid);
        boolean merchantInStock = false;
        boolean isRightBalance = false;
        Double checkBalance = 0.0;

        if(isVaid){
            merchantInStock = userService.merchantInStock(productid);
            if (merchantInStock){
                checkBalance = userService.checkBalance(productid,userid);
                if (checkBalance > 0){
                    isRightBalance = userService.buyProducts(userid,productid,merchantid);
                    if (isRightBalance)
                        return ResponseEntity.status(200).body(new ApiResponse("Successful Buy Operation"));
                }else
                    return ResponseEntity.status(400).body(new ApiResponse("Sorry, your balance less than product price"));

            }else
                return ResponseEntity.status(400).body(new ApiResponse("Sorry, the product out of the stock"));

        }

        return ResponseEntity.status(400).body(new ApiResponse("Sorry, invalid in user id, product id , merchant id"));
    }

}
