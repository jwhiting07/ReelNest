package com.reelnest.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Service // Marks this class as a Spring service bean, so it can be injected into other components
public class S3Service {

    // AWS S3 client instance used to interact with the S3 service
    private final S3Client s3;

    // The name of the S3 bucket being used
    private final String bucket = "reelnest-dev-jesse";

    // Constructor: sets up the S3 client with region and credentials
    public S3Service() {
        this.s3 = S3Client.builder()
                // The AWS region where the bucket is hosted
                .region(Region.US_EAST_1)
                // Loads credentials from the local AWS profile called "reelnest-dev"
                .credentialsProvider(ProfileCredentialsProvider.create("reelnest-dev"))
                .build();
    }

    /**
     * Uploads a file to the S3 bucket
     * @param key the "name" (path/key) under which the file will be stored in S3
     * @param filePath the local filesystem path of the file to upload
     */
    public void uploadFile(String key, String filePath) {
        // Create an S3 PutObjectRequest with bucket name and object key
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        // Uploads the file from the local path to the S3 bucket
        s3.putObject(request, Paths.get(filePath));
    }

    /**
     * Lists all files stored in the S3 bucket
     * @return a list of S3Object metadata (name, size, etc.)
     */
    public List<S3Object> listFiles() {
        // Create a request to list all objects in the bucket
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucket)
                .build();

        // Execute the request and return the contents (list of files in the bucket)
        return s3.listObjectsV2(request).contents();
    }
}