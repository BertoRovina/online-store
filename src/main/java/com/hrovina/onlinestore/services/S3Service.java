package com.hrovina.onlinestore.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import org.slf4j.Logger;

@Service
public class S3Service {

    private Logger logger = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath){

        try {
            File file = new File(localFilePath);
            logger.info("Starting upload...");
            amazonS3.putObject(new PutObjectRequest(bucketName, "test.jpg", file));
            logger.info("Upload completed.");
        }
        catch (AmazonServiceException e){
            logger.info("AmazonServiceException: " + e.getErrorMessage());
            logger.info("Status Code: " + e.getErrorCode());
        }
        catch (AmazonClientException e) {
            logger.info("AmazonClientException: " + e.getMessage());
        }
    }
}
