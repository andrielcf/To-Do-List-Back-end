package com.auth.authtesteuser.service;

import com.auth.authtesteuser.dto.ListNameDTO;
import com.auth.authtesteuser.entity.Category;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.CategoryRepository;
import com.auth.authtesteuser.repository.ListEntityRepository;
import com.auth.authtesteuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListEntityService {

    @Autowired
    private ListEntityRepository listEntityRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    public void createListEntity(ListEntity list){

        //verifica se já existe uma lista com o mesmo nome
        Optional<ListEntity> listOptional = listEntityRepository.findByName(list.getName());
        if (listOptional.isPresent()){
            throw new IllegalArgumentException("A lista " + list.getName() + "já existe");
        }

        Optional<User> userOptional = userRepository.findById(list.getUser().getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            list.setUser(user);
            user.getListEntities().add(list);
        } else {
            throw new IndexOutOfBoundsException("Usuário não encontrado");
        }

        listEntityRepository.save(list);
    }

    public List<ListEntity> getAllListEntitiesByUserId(Long id) {
        return listEntityRepository.findAllByUserId(id);
    }

    public  Optional<ListEntity> getListEntityById(Long id) {
        return listEntityRepository.findById(id);
    }

    public void updateListName(Long id, ListNameDTO name) {
        Optional<ListEntity> optionalList = listEntityRepository.findById(id);

        if (optionalList.isPresent()){
            ListEntity list = optionalList.get();
            list.setName(name.getName());
            listEntityRepository.save(list);
        } else {
            throw new IndexOutOfBoundsException("Lista não encontrada");
        }
    }

    public void deleteListEntity(Long id) {
        listEntityRepository.deleteById(id);
    }
}
