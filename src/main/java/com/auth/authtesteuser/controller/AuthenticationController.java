package com.auth.authtesteuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authtesteuser.dto.AuthenticationDTO;
import com.auth.authtesteuser.dto.LoginResponseDTO;
import com.auth.authtesteuser.dto.RegisterDTO;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.entity.UserRole;
import com.auth.authtesteuser.repository.UserRepository;
import com.auth.authtesteuser.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationDTO data) {
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.generateToken((User)auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity resgister(@Valid @RequestBody RegisterDTO data) {
        if (userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.name(), data.email(), encryptedPassword, UserRole.USER);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}
