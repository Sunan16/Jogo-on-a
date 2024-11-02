package com.jogonca.api_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.Coin;

import jakarta.transaction.Transactional;

public interface CoinRepository extends IJpaRepositoryIdentifier<Coin, Long, String> {

    @Override
    @Transactional
    @Query(
        value = "SELECT c FROM coin c WHERE c.identifier = :identifier",
        nativeQuery = true
    )
    Optional<Coin> findByIdentifier(String i);

}
