package com.chatop.estate.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RentalsResponse {
    private List<RentalDto> rentals;
}
