package com.chatop.estate.repository;

import com.chatop.estate.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
