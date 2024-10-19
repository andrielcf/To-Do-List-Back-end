package com.auth.authtesteuser.service;

import com.auth.authtesteuser.entity.Item;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.repository.ItemRepository;
import com.auth.authtesteuser.repository.ListEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
