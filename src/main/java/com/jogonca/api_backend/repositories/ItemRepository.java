package com.jogonca.api_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.Item;

import jakarta.transaction.Transactional;

public interface ItemRepository extends IJpaRepositoryIdentifier<Item, Long, String> {

    @Override
    @Transactional
    @Query(
        value = "SELECT * FROM item i WHERE i.name = :send",
        nativeQuery = true
        )
    Optional<Item> findByIdentifier(String send);
    
}
