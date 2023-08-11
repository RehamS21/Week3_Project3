package com.example.project3_rehamalmutairi.Service;


import com.example.project3_rehamalmutairi.Model.MerchantStock;
import com.example.project3_rehamalmutairi.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
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

}
