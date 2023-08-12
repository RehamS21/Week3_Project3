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
    private static boolean userPurchase = false;
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

        boolean isAdded = userService.addNewUser(user);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("New user added successfully"));
        else
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the user is already taken"));
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
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, the user id is wrong or updated user id is already taken"));
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
                    if (isRightBalance) {
                        userPurchase = true; // for extra credit, to check if user buy product or not
                        return ResponseEntity.status(200).body(new ApiResponse("Successful Buy Operation"));
                    }
                }else
                    return ResponseEntity.status(400).body(new ApiResponse("Sorry, your balance less than product price"));

            }else
                return ResponseEntity.status(400).body(new ApiResponse("Sorry, the product out of the stock"));

        }

        return ResponseEntity.status(400).body(new ApiResponse("Sorry, invalid in user id, product id , merchant id"));
    }

    // This part for extra credit
    // The idea of extra credit is a user can rate the merchant
    @PutMapping("rate/{merchantid}/{scoreRate}")
    public ResponseEntity setScoreRating(@PathVariable Integer merchantid, @PathVariable Integer scoreRate){
        boolean checkMerchantId = false;
        boolean checkScoreRate = userService.checkScoreRate(scoreRate);
        if(userPurchase){
            if (checkScoreRate) {
                userService.scoreRating(merchantid, scoreRate);
                checkMerchantId = userService.checkValid(merchantid);
                if (checkMerchantId) {
                    userPurchase = false; // for new score rating
                    return ResponseEntity.status(200).body(new ApiResponse("Your rating the merchant successfully"));
                } else
                    return ResponseEntity.status(400).body(new ApiResponse("Invalid merchant id"));
            }else
                return ResponseEntity.status(400).body(new ApiResponse("The score rate should be in the range from (1-5) only"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry, you can't rating the merchant becuase you did not buy from his market"));
    }

}
