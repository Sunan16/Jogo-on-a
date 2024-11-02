package com.jogonca.api_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.jogonca.api_backend.interfaces.IJpaRepositoryIdentifier;
import com.jogonca.api_backend.models.Payment;

import jakarta.transaction.Transactional;

public interface PaymentRepository extends IJpaRepositoryIdentifier<Payment, Long, Long> {

    @Override
    @Transactional
    @Query(
        value = "SELECT * FROM payment p WHERE p.id = :send",
        nativeQuery = true
        )
    Optional<Payment> findByIdentifier(Long send);

}
