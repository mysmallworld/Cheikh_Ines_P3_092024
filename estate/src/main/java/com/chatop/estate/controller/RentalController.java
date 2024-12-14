package com.chatop.estate.controller;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.dto.RentalsResponse;
import com.chatop.estate.dto.SuccessResponse;
import com.chatop.estate.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(
            summary = "Get all rentals",
            description = "Allows a user to get all rentals."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All rentals"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("")
    public ResponseEntity<RentalsResponse> getAllRentals(){
        List<RentalDto> listRentalDto = rentalService.getAllRentals();
        RentalsResponse rentalsResponse = RentalsResponse.builder().rentals(listRentalDto).build();
        return new ResponseEntity<>(rentalsResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Get a rental",
            description = "Allows a user to get a rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "One rental"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Integer id){
        RentalDto rentalDto = rentalService.getRental(id);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Post a rental",
            description = "Allows a user to post a rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("")
    public ResponseEntity<SuccessResponse> postRental(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestParam("name") String name,
                                                      @RequestParam("surface") Double surface,
                                                      @RequestParam("price") Double price,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("picture") MultipartFile picture){
        String rentalCreated = rentalService.postRental(authorizationHeader, name, surface, price, description, picture);
        SuccessResponse successResponse = SuccessResponse.builder().message(rentalCreated).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a rental",
            description = "Allows a user to update his rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateRental(@PathVariable("id") Integer id,
                               @RequestParam(value="name", required = false) String name,
                               @RequestParam(value="surface", required = false) Double surface,
                               @RequestParam(value="price", required = false) Double price,
                               @RequestParam(value="description", required = false) String description,
                               @RequestParam(value="picture", required = false) MultipartFile picture){
        String rentalUpdated = rentalService.updateRental(id, name, surface, price, description, picture);
        SuccessResponse successResponse = SuccessResponse.builder().message(rentalUpdated).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
