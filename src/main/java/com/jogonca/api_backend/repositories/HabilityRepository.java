package com.jogonca.api_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.Hability;

import jakarta.transaction.Transactional;

public interface HabilityRepository extends IJpaRepositoryIdentifier<Hability, Long, String> {

    @Override
    @Transactional
    @Query(
        value = "SELECT * FROM hability h WHERE h.name = :send",
        nativeQuery = true
        )
    Optional<Hability> findByIdentifier(String send);

}
