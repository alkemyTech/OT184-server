package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;



    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationPublicDTO> findOrganizationById(@PathVariable Long id){

        Organization organization= organizationService.findById(id);
        OrganizationPublicDTO organizationPublicDataDTO=model2DTO(organization);
        return ResponseEntity.ok(organizationPublicDataDTO);
    }
    public OrganizationPublicDTO model2DTO(Organization organization){
        return OrganizationPublicDTO.builder()
                .address(organization.getAddress())
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .build();
    }
    @Data
    @Builder
    public static class OrganizationPublicDTO{
        private String name;
        private String image;
        private String address;
        private Integer phone;


    }
}
