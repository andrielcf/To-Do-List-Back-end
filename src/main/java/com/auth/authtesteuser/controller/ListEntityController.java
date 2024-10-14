package com.auth.authtesteuser.controller;

import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.service.ListEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/list")
public class ListEntityController {

    @Autowired
    private ListEntityService listEntityService;


    @PostMapping
    public ResponseEntity<ListEntity> createListEntity(@RequestBody ListEntity listEntity){
        listEntityService.createListEntity(listEntity);

        return new ResponseEntity<>(listEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<ListEntity>> getAllListEntitiesByUserId(@RequestBody User user) {
        List<ListEntity> lists = listEntityService.getAllListEntitiesByUserId(user);

        if (lists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lists, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ListEntity> deleteListEntity(@PathVariable Long id) {

        try {
            listEntityService.deleteListEntity(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
