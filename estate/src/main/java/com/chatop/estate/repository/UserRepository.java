package com.chatop.estate.repository;

import com.chatop.estate.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findByEmail(String email);
}
