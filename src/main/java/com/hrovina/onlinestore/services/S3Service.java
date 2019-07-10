package com.hrovina.onlinestore.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {

    private Logger logger = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String filename = multipartFile.getOriginalFilename();
            InputStream in = multipartFile.getInputStream();
            String contentType =  multipartFile.getContentType();
            return uploadFile(in, filename, contentType);
        } catch (IOException e) {
            throw new RuntimeException("IO Error" + e.getMessage());

        }
    }

    public URI uploadFile(InputStream in, String filename, String contentType){
        try {
            logger.info("Starting upload...");

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            amazonS3.putObject(bucketName, filename, in, metadata);

            logger.info("Upload completed.");

            return amazonS3.getUrl(bucketName, filename).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error converting URL to URI");
        }
    }
}
