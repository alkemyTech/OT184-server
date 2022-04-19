package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;
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

    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public Organization findById(Long id){
        Optional<OrganizationEntity> organizationEntity= organizationRepository.findById(id);
        if(!organizationEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Organization organization=entity2Model(organizationEntity.get());
        return organization;
    }

    @Override
    public Organization toUpdate(Long id, Organization organization){
        OrganizationEntity organizationEntity=organizationRepository.findById(id).orElseThrow();
        organizationEntity.setAddress(organization.getAddress());
        organizationEntity.setName(organization.getName());
        organizationEntity.setPhone(organization.getPhone());
        organizationEntity.setImage(organization.getImage());
        organizationEntity.setFacebook(organization.getFacebook());
        organizationEntity.setInstagram(organization.getInstagram());
        organizationEntity.setInstagram(organization.getInstagram());
        return entity2Model(organizationRepository.save(organizationEntity));
    }

    public OrganizationEntity model2Entity(Organization organization){
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

    public Organization entity2Model(OrganizationEntity organizationEntity){
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
