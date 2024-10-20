package com.auth.authtesteuser.repository;

import com.auth.authtesteuser.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListEntityRepository extends JpaRepository<ListEntity, Long> {

    Optional<ListEntity> findByName(String name);
    List<ListEntity> findAllByUserId(Long userId);
}
