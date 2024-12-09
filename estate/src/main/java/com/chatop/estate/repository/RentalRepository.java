package com.chatop.estate.repository;

import com.chatop.estate.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<Rental, UUID> {
    Rental findByUserId(UUID userId);
}
