package com.alkemy.ong.web.members.controller;


import com.alkemy.ong.domain.members.model.Members;
import com.alkemy.ong.domain.members.service.Gateway;
import com.alkemy.ong.web.members.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("members")
public class MembersController {


    private final Gateway gateway;

    @Autowired
    public MembersController (Gateway gateway){
        this.gateway = gateway;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody MemberDTO memberDTO){
         Members members =
                 gateway.save(
                     Members
                         .builder().name(memberDTO.getName())
                         .facebookUrl(memberDTO.getFacebookUrl()).instagramUrl(memberDTO.getInstagramUrl())
                         .linkedinUrl(memberDTO.getLinkedinUrl()).image(memberDTO.getImage())
                         .description(memberDTO.getDescription()).build());

    return ResponseEntity.status(HttpStatus.CREATED).body(
            MemberDTO
                    .builder().id(members.getId()).name(members.getName())
                    .facebookUrl(members.getFacebookUrl()).instagramUrl(members.getInstagramUrl())
                    .linkedinUrl(members.getLinkedinUrl()).image(members.getImage())
                    .description(members.getDescription()).build());
    }


    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll(){
        List<Members> membersModels = gateway.getAll();
        List<MemberDTO> memberDTO = new ArrayList<>();
        membersModels.forEach((Members) -> memberDTO.add(
                MemberDTO.builder()
                .id(Members.getId())
                .name(Members.getName())
                .facebookUrl(Members.getFacebookUrl())
                .instagramUrl(Members.getInstagramUrl())
                .linkedinUrl(Members.getLinkedinUrl())
                .image(Members.getImage())
                .description(Members.getDescription())
                .build()
        ));
        return ResponseEntity.ok().body(memberDTO);
    }



}
