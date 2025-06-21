package com.example.demojwtt.repository;

import com.example.demojwtt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca um usuário pelo nome de login (username)
    Optional<User> findByUsername(String username);
}
