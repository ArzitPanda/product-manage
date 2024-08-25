package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

    private static final String IMAGE_DIRECTORY = "http://localhost:8080/images/";
    public static String storeImage(MultipartFile imageFile) {
        try {

            String imageDirectory = "src/main/resources/static/images/";
            String fileName= imageFile.getOriginalFilename().replaceAll("\\s+", "-");
            Path imagePath = Paths.get(imageDirectory +fileName);

            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageFile.getBytes());
            return IMAGE_DIRECTORY+fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }
}
