package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.bucket.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
public class BucketController {

  private final AmazonClient amazonClient;

  @Autowired
  BucketController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  @PostMapping
  public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
    return this.amazonClient.uploadFile(file);
  }

  @DeleteMapping
  public String deleteFile(@RequestPart(value = "url") String fileUrl) {
    return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
  }
}