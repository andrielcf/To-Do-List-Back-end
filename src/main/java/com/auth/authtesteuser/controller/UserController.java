package com.auth.authtesteuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user){
        userRepository.save(user);

        return user;
        
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    
}
