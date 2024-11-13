package com.auth.authtesteuser.service;

import com.auth.authtesteuser.dto.ListNameDTO;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.ListEntityRepository;
import com.auth.authtesteuser.repository.UserRepository;
import com.auth.authtesteuser.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ListEntityService {

    @Autowired
    private ListEntityRepository listEntityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;


    public void createListEntity(ListEntity list, String token){

        if (list.getName().isEmpty()){
            throw new RuntimeException("O nome da lista não pode estar vazio");
        }

        String userEmail = tokenService.extractSubject(token);
        User user = userRepository.findByEmail(userEmail);

        //verifica se já existe uma lista com o mesmo nome
        List<ListEntity> userLists = user.getListEntities();
        for (ListEntity _list : userLists) {
            boolean alreadyExists = _list.getName().equals(list.getName());

            if (alreadyExists) {
                throw new IllegalArgumentException("A lista " + list.getName() + " já existe");
            }
        }

        list.setUser(user);
        user.getListEntities().add(list);
        listEntityRepository.save(list);
    }

    public List<ListEntity> getAllListEntitiesByUserId(String token) {
        String userEmail = tokenService.extractSubject(token);

        User user = userRepository.findByEmail(userEmail);

        return listEntityRepository.findAllByUserId(user.getId());
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
        Optional<ListEntity> optionalList = listEntityRepository.findById(id);

        if (optionalList.isPresent()) {
            listEntityRepository.deleteById(id);
        } else {
            throw new IndexOutOfBoundsException("Lista não encontrada");
        }

    }
}
