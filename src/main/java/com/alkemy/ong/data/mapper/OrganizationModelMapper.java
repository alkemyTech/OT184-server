package com.alkemy.ong.data.mapper;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.domain.organizations.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationModelMapper {

    public OrganizationEntity model2Entity(Organization organization){
        return OrganizationEntity.builder()
                .address(organization.getAddress())
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .build();

    }

    public Organization entity2Model(OrganizationEntity organizationEntity){
        return Organization.builder()
                .name(organizationEntity.getName())
                .phone(organizationEntity.getPhone())
                .image(organizationEntity.getImage())
                .address(organizationEntity.getAddress())
                .build();
    }
}
