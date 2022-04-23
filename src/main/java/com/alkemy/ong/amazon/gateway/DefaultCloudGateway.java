package com.alkemy.ong.amazon.gateway;

import com.alkemy.ong.domain.cloud.CloudInput;
import com.alkemy.ong.domain.cloud.CloudGateway;
import com.alkemy.ong.domain.cloud.CloudOutput;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

@Component
public class DefaultCloudGateway implements CloudGateway {

    @Value("${AMAZON_S3_BUCKET_NAME}")
    private String BUCKET;

    public CloudOutput save(CloudInput cloud) throws IOException {
        String extension = StringUtils.getFilenameExtension(cloud.getFile().getOriginalFilename());
        String key = String.format("%s.%s", UUID.randomUUID(), extension);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(cloud.getFile().getContentType());

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(BUCKET, key, cloud.getFile().getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);

        accesAmazon(putObjectRequest).putObject(putObjectRequest);

        return toCloudOuput(key, getObjectUrl(key));
    }

    public String getObjectUrl(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);
    }

    public CloudOutput toCloudOuput(String key, String url) {
        return CloudOutput.builder()
                .key(key)
                .url(url)
                .build();
    }

    public AmazonS3 accesAmazon(PutObjectRequest putObjectRequest){
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .build();
    }
}

