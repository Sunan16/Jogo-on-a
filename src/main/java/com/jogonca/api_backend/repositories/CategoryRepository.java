package com.jogonca.api_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.Category;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

public interface CategoryRepository extends IJpaRepositoryIdentifier<Category, Long, String> {

    @Override
    @Transactional
    @Query(
        value = "SELECT * FROM category c WHERE c.name = :send",
        nativeQuery = true
        )
    Optional<Category> findByIdentifier(String send);

    @Transactional
    @Query(
        value = "SELECT * FROM item i WHERE i.category_id = :id",
        nativeQuery = true
        )
    public List<Tuple> findAllItens(@Param("id") Long id);

}
