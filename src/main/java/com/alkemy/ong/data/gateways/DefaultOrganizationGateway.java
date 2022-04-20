package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;

import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationGateway;

import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        Organization returnModel = this.toModel(organizationEntity.get());
        return returnModel;
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

    private Organization toModel(OrganizationEntity organizationEntity){
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

}
