package com.auth.authtesteuser.service;

import com.auth.authtesteuser.entity.Item;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.repository.ItemRepository;
import com.auth.authtesteuser.repository.ListEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ListEntityRepository listEntityRepository;


    public void createItem(Item item) {

        //verifica se a lista existe
        Optional<ListEntity> optionalList = listEntityRepository.findById(item.getListEntity().getId());
        if (optionalList.isPresent()){
            ListEntity list = optionalList.get();

            //Define a lista do item
            item.setListEntity(list);
            //Adiciona o item a lista
            list.getItems().add(item);
            itemRepository.save(item);
        } else {
            throw new IndexOutOfBoundsException("Lista não encontrada");
        }
    }

    public List<Item> getAllItemsByListId(Long id) {
        return itemRepository.findAllByListEntityId(id);
    }

    public void updateItem(Long id, Map<String, Object> updates) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "description":
                        item.setDescription((String) value);
                        break;

                    case "state":
                        item.setState((Boolean) value);
                        break;

                    default:
                        throw new RuntimeException("Campo desconhecido: " + key);
                }
            });

            itemRepository.save(item);
        } else {
            throw new IndexOutOfBoundsException("Item não encontrado");
        }

    }

    public void deleteItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
        } else {
            throw new IndexOutOfBoundsException("Item não encontrado");
        }
    }
}
