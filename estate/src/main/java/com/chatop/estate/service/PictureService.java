package com.chatop.estate.service;

import com.chatop.estate.model.Rental;
import com.nimbusds.jose.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Value("${ProjectPath}")
    private String projectPath;

    private final ResourceLoader resourceLoader;

    public PictureService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void selectPicture(MultipartFile picture, Rental rental) {
        try {
            if (picture != null && !picture.isEmpty()) {
                String fileName = picture.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/estate/uploads";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                picture.transferTo(filePath.toFile());

                rental.setPicture(projectPath + fileName);
            } else {
                rental.setPicture(null);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getMessage());
        }
    }

    public Pair<Resource, HttpHeaders> getPicture(String filename) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/estate/uploads";
            Path filePath = Paths.get(uploadDir).resolve(filename);

            Resource resource = resourceLoader.getResource("file:" + filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));

            return Pair.of(resource, headers);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
