package com.alkemy.ong.web.mappers;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.web.dto.OrganizationPublicDataDTO;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationPublicDataDTO model2DTO(Organization organization){
       return OrganizationPublicDataDTO.builder()
                .address(organization.getAddress())
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .build();
    }

    public Organization DTO2Model(OrganizationPublicDataDTO organizationPublicDataDTO){
        return Organization.builder()
                .address(organizationPublicDataDTO.getAddress())
                .name(organizationPublicDataDTO.getName())
                .image(organizationPublicDataDTO.getName())
                .phone(organizationPublicDataDTO.getPhone())
                .build();
    }
}
