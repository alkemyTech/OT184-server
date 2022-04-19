package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import com.alkemy.ong.web.dto.OrganizationPublicDataDTO;
import com.alkemy.ong.web.mappers.OrganizationMapper;
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

    @Autowired
    private OrganizationMapper organizationMapper;

    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationPublicDataDTO> findOrganizationById(@PathVariable Long id){

        Organization organization= organizationService.findById(id);
        OrganizationPublicDataDTO organizationPublicDataDTO=organizationMapper.model2DTO(organization);
        return ResponseEntity.ok(organizationPublicDataDTO);
    }
}
