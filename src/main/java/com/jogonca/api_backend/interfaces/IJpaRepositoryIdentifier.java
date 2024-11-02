package com.jogonca.api_backend.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaRepositoryIdentifier<E, I, TypeIdentifier> extends JpaRepository<E, I> {
    
    Optional<E> findByIdentifier(TypeIdentifier i);
    
}
