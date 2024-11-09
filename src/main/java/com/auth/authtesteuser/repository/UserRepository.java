package com.auth.authtesteuser.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.auth.authtesteuser.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByEmail(String email);
    
}
