package com.auth.authtesteuser.controller;

import com.auth.authtesteuser.entity.Item;
import com.auth.authtesteuser.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<Object> createItem(@RequestBody Item item) {
        try {
            itemService.createItem(item);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Item>> getAllItemsByListId(@PathVariable Long id){
        List<Item> itemsList = itemService.getAllItemsByListId(id);

        if (itemsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(itemsList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            itemService.updateItem(id, updates);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
