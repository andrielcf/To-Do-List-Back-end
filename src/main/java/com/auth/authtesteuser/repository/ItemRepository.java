package com.auth.authtesteuser.repository;

import com.auth.authtesteuser.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByListEntityId(Long listId);
}
