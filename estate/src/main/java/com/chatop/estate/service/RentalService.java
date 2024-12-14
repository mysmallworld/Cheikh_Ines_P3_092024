package com.chatop.estate.service;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.model.Rental;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.RentalRepository;
import com.chatop.estate.repository.UserRepository;
import com.chatop.estate.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private AuthService authService;

    public List<RentalDto> getAllRentals() {
        try {
            List<Rental> rentals = rentalRepository.findAll();
            return rentals.stream()
                    .map(rentalMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public RentalDto getRental(Integer id) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found"));
            return rentalMapper.toDto(rental);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public String postRental(String authorizationHeader, String name, Double surface, Double price, String description, MultipartFile picture) {
        try {

            Rental rental = new Rental();
            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);
            pictureService.selectPicture(picture, rental);

            String userEmail = authService.getUser(authorizationHeader).getEmail();
            User owner = userRepository.findByEmail(userEmail);
            rental.setUser(owner);

            LocalDateTime now = LocalDateTime.now();
            rental.setCreatedAt(now);
            rental.setUpdatedAt(now);

            rentalRepository.save(rental);

            return "message: Rental created !";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public String updateRental(Integer id, String name, Double surface, Double price, String description) {
        try {
            Rental rental = rentalRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found"));

            rental.setName(name != null ? name : rental.getName());
            rental.setSurface(surface != null ? surface : rental.getSurface());
            rental.setPrice(price != null ? price : rental.getPrice());
            rental.setDescription(description != null ? description : rental.getDescription());
            rental.setUpdatedAt(LocalDateTime.now());

            rentalRepository.save(rental);

            return "message: Rental updated !";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
