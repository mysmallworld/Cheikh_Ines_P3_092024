package com.chatop.estate.controller;

import com.chatop.estate.service.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService =  pictureService;
    }

    @Operation(
            summary = "Get a picture",
            description = "Allows the user to get a picture his rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture get with success"),
            @ApiResponse(responseCode = "404", description = "Picture not found")
    })
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
            Resource resource = pictureService.getPicture(filename).getLeft();
            HttpHeaders headers = pictureService.getPicture(filename).getRight();
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
