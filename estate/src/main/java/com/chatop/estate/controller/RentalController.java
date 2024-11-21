package com.chatop.estate.controller;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.service.RentalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("")
    public List<RentalDto> getAllRentals(){
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalDto getRental(@PathVariable String id){
        return rentalService.getRental(id);
    }

    @PostMapping("/{id}")
    public String postRental(@PathVariable("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("surface") Double surface,
                             @RequestParam("price") Double price,
                             @RequestParam("description") String description,
                             @RequestPart(value = "picture", required = false) MultipartFile picture){
        return rentalService.postRental(id, name, surface, price, description, picture);
    }

    @PutMapping("/{id}")
    public String updateRental(@PathVariable("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("surface") Double surface,
                               @RequestParam("price") Double price,
                               @RequestParam("description") String description,
                               @RequestPart(value = "picture", required = false) MultipartFile picture){
        return rentalService.updateRental(id, name, surface, price, description, picture);
    }
}
