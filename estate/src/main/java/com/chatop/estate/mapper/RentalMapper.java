package com.chatop.estate.mapper;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public RentalDto toDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setPicture(rental.getPicture());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setOwnerId(rental.getUser() != null ? rental.getUser().getId() : null);
        rentalDto.setCreated_at(rental.getCreatedAt());
        rentalDto.setUpdated_at(rental.getUpdatedAt());
        return rentalDto;
    }
}
