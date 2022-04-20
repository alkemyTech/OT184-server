package com.alkemy.ong.web.controllers;


import com.alkemy.ong.domain.members.Members;
import com.alkemy.ong.domain.members.MemberGateway;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("members")
public class MembersController {

    private final MemberGateway gateway;

    public MembersController (MemberGateway gateway){
        this.gateway = gateway;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody MemberDTO memberDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                toMembers(gateway.save(toMemberDTO(memberDTO))));
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll(){
        return ResponseEntity.ok().body(
                gateway.getAll()
                .stream()
                .map(e -> toMembers(e))
                .collect(toList()));
    }

    private Members toMemberDTO(MemberDTO memberDTO){
        return Members
                .builder().name(memberDTO.getName())
                .facebookUrl(memberDTO.getFacebookUrl()).instagramUrl(memberDTO.getInstagramUrl())
                .linkedinUrl(memberDTO.getLinkedinUrl()).image(memberDTO.getImage())
                .description(memberDTO.getDescription()).build();
    }

    private MemberDTO toMembers(Members members){
        return MemberDTO.builder()
                .id(members.getId())
                .name(members.getName())
                .facebookUrl(members.getFacebookUrl())
                .instagramUrl(members.getInstagramUrl())
                .linkedinUrl(members.getLinkedinUrl())
                .image(members.getImage())
                .description(members.getDescription())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        gateway.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Data
    @Builder
    private static class MemberDTO {
        private Long id;
        @NotNull
        private String name;
        private String facebookUrl;
        private String instagramUrl;
        private String linkedinUrl;
        @NotNull
        private String image;
        private String description;
    }




}
