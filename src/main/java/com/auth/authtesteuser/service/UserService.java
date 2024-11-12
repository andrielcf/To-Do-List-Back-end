package com.auth.authtesteuser.service;

import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.UserRepository;
import com.auth.authtesteuser.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(String token){
        String userEmail = tokenService.extractSubject(token);

        User user = userRepository.findByEmail(userEmail);

        userRepository.deleteById(user.getId());
    }

}
