package com.leetcode2024spring.ecommercedemo1.controller;

import com.leetcode2024spring.ecommercedemo1.collection.Product;
import com.leetcode2024spring.ecommercedemo1.collection.User;
import com.leetcode2024spring.ecommercedemo1.service.ProductServiceImp;
import com.leetcode2024spring.ecommercedemo1.service.UserService;
import com.leetcode2024spring.ecommercedemo1.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserServiceImp userService;
    @Autowired
    private ProductServiceImp productService;


    @GetMapping("/getUser")
    public User findByEmail(String email){
        return userService.findByEmail(email);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        boolean loginSuccess = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(loginSuccess);
    }

    @GetMapping("/getWishlist")
    public List<Product> getWishlist(String email){
        String[] pIdList = userService.findByEmail(email).getWishlist();
        List<Product> res = new LinkedList<>();
        for(String s : pIdList){
            Product pro = productService.findProductById(s);
            res.add(pro);
        }
        return res;
    }
}
