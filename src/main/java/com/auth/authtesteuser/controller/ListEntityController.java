package com.auth.authtesteuser.controller;

import com.auth.authtesteuser.dto.ListNameDTO;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.UserRepository;
import com.auth.authtesteuser.security.TokenService;
import com.auth.authtesteuser.service.ListEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/list")
public class ListEntityController {

    @Autowired
    private ListEntityService listEntityService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Object> createListEntity(@RequestBody ListEntity listEntity, @RequestHeader("Authorization") String token){
        
        String userEmail = tokenService.extractSubject(token);

        User user = userRepository.findByEmail(userEmail);

        listEntity.setUser(user);

        try {
            listEntityService.createListEntity(listEntity);
            return new ResponseEntity<>(listEntity, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public  ResponseEntity<List<ListEntity>> getAllListEntitiesByUserId(@RequestHeader("Authorization") String token) {
        
        String userEmail = tokenService.extractSubject(token);

        User user = userRepository.findByEmail(userEmail);

        List<ListEntity> lists = listEntityService.getAllListEntitiesByUserId(user.getId());

        if (lists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/gettoken")
    public ResponseEntity getToken(@RequestHeader("Authorization") String token){

        token = token.replace("Bearer ", "");
        
        String userEmail = tokenService.extractSubject(token);

        User user = userRepository.findByEmail(userEmail);

        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ListEntity> getListEntityById(@PathVariable Long id) {
         Optional<ListEntity> optionalList = listEntityService.getListEntityById(id);

         if (optionalList.isPresent()) {
             ListEntity list = optionalList.get();
             return new ResponseEntity<>(list, HttpStatus.OK);
         }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateListName(@PathVariable Long id, @RequestBody ListNameDTO name) {
        try {
            listEntityService.updateListName(id, name);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteListEntity(@PathVariable Long id) {
        try {
            listEntityService.deleteListEntity(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
