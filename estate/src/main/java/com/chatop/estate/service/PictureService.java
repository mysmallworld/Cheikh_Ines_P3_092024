package com.chatop.estate.service;

import com.chatop.estate.model.Rental;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Value("${ProjectPath}")
    private String projectPath;

    public void selectPicture(MultipartFile picture, Rental rental) {
        try {
            if (picture != null && !picture.isEmpty()) {
                String fileName = picture.getOriginalFilename();
                String uploadDir = System.getProperty("user.home") + projectPath + "/estate/src/main/resources/uploads";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                picture.transferTo(filePath.toFile());

                rental.setPicture(filePath.toAbsolutePath().toString());
            } else {
                rental.setPicture(null);
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while processing the picture: " + e.getMessage(), e);
        }
    }

}
