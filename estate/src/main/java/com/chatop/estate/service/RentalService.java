package com.chatop.estate.service;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.model.Rental;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.RentalRepository;
import com.chatop.estate.repository.UserRepository;
import com.chatop.estate.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalMapper rentalMapper;

    public List<RentalDto> getAllRentals() {
        try {
            List<Rental> rentals = rentalRepository.findAll();
            return rentals.stream()
                    .map(rentalMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all rentals: " + e.getMessage());
        }
    }

    public RentalDto getRental(String id) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rental not found"));
            return rentalMapper.toDto(rental);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching a rental: " + e.getMessage());
        }
    }

    public String postRental(String ownerId, String name, Double surface, Double price, String description, MultipartFile picture) {
        try {

            Rental rental = new Rental();
            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);

            User owner = userRepository.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            rental.setUser(owner);

            if (picture != null && !picture.isEmpty()) {

                String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();

                String uploadDir = "uploads/";
                File uploadFile = new File(uploadDir + fileName);

                if (!uploadFile.getParentFile().exists()) {
                    uploadFile.getParentFile().mkdirs();
                }

                picture.transferTo(uploadFile);

                rental.setPicture(uploadFile.getAbsolutePath());
            } else {
                rental.setPicture(null);
            }

            LocalDateTime now = LocalDateTime.now();
            rental.setCreatedAt(now);
            rental.setUpdatedAt(now);

            rentalRepository.save(rental);

            return "message: Rental created!";
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while processing the picture: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during rental posting: " + e.getMessage());
        }
    }


    public String updateRental(String id, String name, Double surface, Double price, String description, MultipartFile picture) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rental not found"));

            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            //rental.setPicture(rentalDto.getPicture());
            rental.setDescription(description);

            if (rental.getUser().getId() != null) {
                User owner = userRepository.findById(rental.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("Owner not found"));
                rental.setUser(owner);
            }

            rentalRepository.save(rental);

            return "message: Rental updated !";
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during rental updating: " + e.getMessage());
        }
    }
}
