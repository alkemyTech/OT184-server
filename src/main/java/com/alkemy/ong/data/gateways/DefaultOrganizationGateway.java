package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;

import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationGateway;

import org.springframework.stereotype.Component;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class DefaultOrganizationGateway implements OrganizationGateway {


    private final OrganizationRepository organizationRepository;

    public DefaultOrganizationGateway(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }


    @Override
    public Organization findById(Long id) {
        Optional<OrganizationEntity> organizationEntity = organizationRepository.findById(id);
        organizationEntity.orElseThrow(() -> new ResourceNotFoundException("ID"));
        Organization returnModel = this.toModelComplete(organizationEntity.get());
        return returnModel;
    }

    @Override
    public Organization toUpdate(Long id, Organization organization){
        OrganizationEntity organizationEntity=organizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID was not found"));
        organizationEntity.setAddress(organization.getAddress());
        organizationEntity.setName(organization.getName());
        organizationEntity.setPhone(organization.getPhone());
        organizationEntity.setImage(organization.getImage());
        organizationEntity.setInstagram(organization.getInstagram());
        organizationEntity.setFacebook(organization.getFacebook());
        organizationEntity.setLinkedin(organization.getLinkedin());
        return toModel(organizationRepository.save(organizationEntity));
    }


    private OrganizationEntity toEntity(Organization organization){
        return OrganizationEntity.builder()
                .address(organization.getAddress())
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .facebook(organization.getFacebook())
                .instagram(organization.getInstagram())
                .linkedin(organization.getLinkedin())
                .build();

    }

    public static Organization toModel(OrganizationEntity organizationEntity){
        return Organization.builder()
                .name(organizationEntity.getName())
                .phone(organizationEntity.getPhone())
                .image(organizationEntity.getImage())
                .address(organizationEntity.getAddress())
                .facebook(organizationEntity.getFacebook())
                .instagram(organizationEntity.getInstagram())
                .linkedin(organizationEntity.getLinkedin())
                .build();
    }

    public Organization toModelComplete(OrganizationEntity organizationEntity){
        return Organization.builder()
                .name(organizationEntity.getName())
                .phone(organizationEntity.getPhone())
                .image(organizationEntity.getImage())
                .address(organizationEntity.getAddress())
                .facebook(organizationEntity.getFacebook())
                .instagram(organizationEntity.getInstagram())
                .linkedin(organizationEntity.getLinkedin())
                .slides(organizationEntity.getSlides()
                        .stream()
                        .map(slide -> DefaultSlideGateway.toModelBasic(slide))
                        .collect(toList()))
                .build();
    }

}
