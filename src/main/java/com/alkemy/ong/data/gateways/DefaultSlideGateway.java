package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.SlidesEntity;
import com.alkemy.ong.data.repositories.SlidesRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.slide.Slide;
import com.alkemy.ong.domain.slide.SlideGateway;
import org.springframework.stereotype.Component;

@Component
public class DefaultSlideGateway implements SlideGateway {

    private final SlidesRepository slidesRepository;

    public DefaultSlideGateway(SlidesRepository slidesRepository){
        this.slidesRepository = slidesRepository;
    }

    @Override
    public Slide findById(Long id) {
        SlidesEntity slidesEntity = slidesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID"));
        return toModel(slidesEntity);
    }

    @Override
    public void delete(Long id) {
        SlidesEntity slidesEntity = slidesRepository.findById(id).orElseThrow(() -> new RuntimeException("ID"));
        slidesEntity.setIsDeleted(true);
        slidesRepository.save(slidesEntity);
    }

    public Slide toModel(SlidesEntity slidesEntity){
        return Slide.builder()
                .id(slidesEntity.getId())
                .imageUrl(slidesEntity.getImageUrl())
                .text(slidesEntity.getText())
                .order(slidesEntity.getOrder())
                .organization(DefaultOrganizationGateway.toModel(slidesEntity.getOrganization()))
                .build();
    }


}
