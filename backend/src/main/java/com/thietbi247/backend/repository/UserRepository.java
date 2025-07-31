package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findByUserName(String name);
    Optional<User> findByEmail(String email);

}
