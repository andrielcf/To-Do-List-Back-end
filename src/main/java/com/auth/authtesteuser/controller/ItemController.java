package com.auth.authtesteuser.controller;

import com.auth.authtesteuser.entity.Item;
import com.auth.authtesteuser.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        itemService.createItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Item>> getAllItemsByListId(@PathVariable Long id){
        List<Item> itemsList = itemService.getAllItemsByListId(id);

        if (itemsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(itemsList, HttpStatus.OK);
    }
}
