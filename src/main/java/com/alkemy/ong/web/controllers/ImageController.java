package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.cloud.CloudInput;
import com.alkemy.ong.domain.cloud.CloudGateway;
import com.alkemy.ong.domain.cloud.CloudOutput;
import lombok.Builder;
import lombok.Data;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private CloudGateway cloudGateway;

    public ImageController(CloudGateway cloudGateway){
        this.cloudGateway = cloudGateway;
    }

    @PostMapping("${AMAZON_S3_ENDPOINT_URL}")
    public ResponseEntity<CloudOutputDTO> uploader(@RequestParam("file") MultipartFile multipartFile) throws IOException{
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toCloudDTO(
                        cloudGateway.save(
                                toCloudInput(multipartFile))));
    }

    public CloudInput toCloudInput(MultipartFile multipartFile){
        return CloudInput.builder().file(multipartFile).build();
    }

    public CloudOutputDTO toCloudDTO(CloudOutput cloudOutput){
        return CloudOutputDTO.builder().key(cloudOutput.getKey()).url(cloudOutput.getUrl()).build();
    }

    @Data
    @Builder
    private static class CloudInputDTO{
        private MultipartFile file;
    }

    @Data
    @Builder
    private static class CloudOutputDTO{
        private String key;
        private String url;
    }

}
