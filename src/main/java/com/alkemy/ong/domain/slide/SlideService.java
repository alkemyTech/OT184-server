package com.alkemy.ong.domain.slide;

import com.alkemy.ong.domain.cloud.CloudGateway;
import com.alkemy.ong.domain.cloud.CloudInput;
import com.alkemy.ong.domain.cloud.CloudService;

import org.springframework.transaction.annotation.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.*;

import java.util.List;

@Service
public class SlideService {

    private final SlideGateway slideGateway;
    private final CloudService cloudService;
    private final BASE64DecodedMultipartFile base64DecodedMultipartFile;
    private final CloudGateway cloudGateway;





    public SlideService(SlideGateway slideGateway, CloudService cloudService, BASE64DecodedMultipartFile base64DecodedMultipartFile, CloudGateway cloudGateway){
        this.slideGateway = slideGateway;
        this.cloudService = cloudService;


        this.base64DecodedMultipartFile = base64DecodedMultipartFile;
        this.cloudGateway = cloudGateway;
    }

    public Slide findById(Long id){return slideGateway.findById(id);}

    public void delete(Long id){slideGateway.delete(id);}

    public List<Slide> findAll() {return slideGateway.findAll();}


    @Transactional
    public Slide save(Slide slide, Long id) throws IOException {

        BASE64DecodedMultipartFile multiPart =
                new BASE64DecodedMultipartFile(Base64.decodeBase64(slide.getImageUrl()));
        cloudService.save(CloudInput.builder().file(multiPart).build());
        return slideGateway.save(slide, id); }



}
