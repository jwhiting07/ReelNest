package com.reelnest.controller;

import com.reelnest.service.S3Service;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

@RestController // Marks this class as a REST controller (Spring will return JSON responses by default)
@RequestMapping("/api/files") // Base URL path for all endpoints in this controller
public class FileController {

    // Service class that handles interaction with AWS S3
    private final S3Service s3Service;

    // Constructor injection of the S3Service bean
    public FileController(S3Service s3Service){
        this.s3Service = s3Service;
    }

    /**
     * GET /api/files
     * Endpoint to list all files in the S3 bucket.
     * Returns a JSON array of S3Object metadata (key, size, last modified, etc.).
     * @return the list of files
     */
    @GetMapping
    public List<S3Object> listFiles() {
        return s3Service.listFiles();
    }

    /**
     * POST /api/files/upload
     * Endpoint to upload a file to S3
     * @param key The object key (filename/path in the bucket)
     * @param path The local file path of the file to upload
     * @return A confirmation message with the uploaded key
     *
     * Example request:
     * POST http://localhost:8080/api/files/upload?key=test.txt&path=/Users/User/Desktop/text.txt
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam String key, @RequestParam String path) {
        s3Service.uploadFile(key, path);
        return "Uploaded " + key;
    }
}