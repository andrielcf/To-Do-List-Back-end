package com.auth.authtesteuser.service;

import com.auth.authtesteuser.entity.Category;
import com.auth.authtesteuser.entity.ListEntity;
import com.auth.authtesteuser.entity.User;
import com.auth.authtesteuser.repository.CategoryRepository;
import com.auth.authtesteuser.repository.ListEntityRepository;
import com.auth.authtesteuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

        //Itera sobre a lista de categorias da lista
        for (Category category : list.getCategories()) {

            //Verifica se a categoria existe atraves do id informado
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            if (optionalCategory.isPresent()){

                //Adiciona as categorias a lista
                list.getCategories().add(category);
                //Adiciona a lista nas listas da categoria
                category.getListEntities().add(list);
            } else {
                throw new IndexOutOfBoundsException("Categoria não encontrada");
            }
        }
        listEntityRepository.save(list);
    }

    public List<ListEntity> getAllListEntitiesByUserId(User user) {
        return listEntityRepository.findAllByUserId(user.getId());
    }

    public  Optional<ListEntity> getListEntityById(Long id) {
        return listEntityRepository.findById(id);
    }

    public void deleteListEntity(Long id) {
        listEntityRepository.deleteById(id);
    }
}
