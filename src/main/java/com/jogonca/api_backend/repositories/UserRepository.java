package com.jogonca.api_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends IJpaRepositoryIdentifier<User, Long, String> {

    @Override
    @Transactional
    @Query(
        value = "SELECT * FROM users u WHERE LOWER(u.email) = LOWER(:send)",
        nativeQuery = true
        )
    Optional<User> findByIdentifier(String send);

    @Transactional
    @Query(
        value = "SELECT * FROM users u WHERE LOWER(u.email) = LOWER(:email) AND u.password_hash = :pass",
        nativeQuery = true
        )
    Optional<User> login(String email, String pass);

}
