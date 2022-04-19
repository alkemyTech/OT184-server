package com.alkemy.ong.web;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationPublicDTO> findOrganizationById(@PathVariable Long id){

        Organization organization= organizationService.findById(id);
        OrganizationPublicDTO organizationPublicDTO=model2DTO(organization);
        return ResponseEntity.ok(organizationPublicDTO);
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationPublicDTO> upDate(@PathVariable Long id,@RequestBody OrganizationPublicDTO dto){
        Organization organization=organizationService.upDate(id,DTO2Model(dto));

        return new ResponseEntity<>(model2DTO(organization), HttpStatus.OK);
    }

    public OrganizationPublicDTO model2DTO(Organization organization){
        return OrganizationPublicDTO.builder()
                .address(organization.getAddress())
                .name(organization.getName())
                .image(organization.getImage())
                .phone(organization.getPhone())
                .facebook(organization.getFacebook())
                .instagram(organization.getInstagram())
                .linkedin(organization.getLinkedin())
                .build();
    }

    public Organization DTO2Model(OrganizationPublicDTO organizationPublicDataDTO){
        return Organization.builder()
                .address(organizationPublicDataDTO.getAddress())
                .name(organizationPublicDataDTO.getName())
                .image(organizationPublicDataDTO.getName())
                .phone(organizationPublicDataDTO.getPhone())
                .linkedin(organizationPublicDataDTO.getLinkedin())
                .instagram(organizationPublicDataDTO.getInstagram())
                .facebook(organizationPublicDataDTO.getFacebook())
                .build();
    }
    @Data
    @Builder
    public static class OrganizationPublicDTO{
        private String name;
        private String image;
        private String address;
        private Integer phone;
        private String facebook;
        private String linkedin;
        private String instagram;

    }
}
