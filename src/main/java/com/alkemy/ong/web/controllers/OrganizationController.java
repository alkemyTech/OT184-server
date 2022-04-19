package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationService;
import com.alkemy.ong.web.dto.OrganizationPublicDataDTO;
import com.alkemy.ong.web.mappers.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationPublicDataDTO> upDate(@PathVariable Long id,@RequestBody OrganizationPublicDataDTO dto){
        Organization organization=organizationService.upDate(id,organizationMapper.DTO2Model(dto));

        return new ResponseEntity<>(organizationMapper.model2DTO(organization), HttpStatus.OK);
    }
}
