package com.chatop.estate.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 30)
    private String name;

    @Column(length = 10000)
    private Double surface;

    @Column(length = 1000000000)
    private Double price;

    @Column(length = 100)
    private String picture;

    @Column(length = 200)
    private String description;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private User user;
}
