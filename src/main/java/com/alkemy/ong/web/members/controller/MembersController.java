package com.alkemy.ong.web.members.controller;


import com.alkemy.ong.data.entity.MemberEntity;
import com.alkemy.ong.domain.members.model.Members;
import com.alkemy.ong.domain.members.service.Gateway;
import com.alkemy.ong.domain.members.service.MemberService;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("members")
public class MembersController {

    private final MemberService memberService;

    public MembersController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll() {
        return ResponseEntity.ok().body(
                memberService.getAll()
                .stream()
                .map(e -> toDTO(e))
                .collect(Collectors.toList()));
    }

    private MemberDTO toDTO(Members members){
        return MemberDTO
                .builder().id(members.getId()).name(members.getName())
                .facebookUrl(members.getFacebookUrl()).instagramUrl(members.getInstagramUrl())
                .linkedinUrl(members.getLinkedinUrl()).image(members.getImage())
                .description(members.getDescription()).build();
    }




    @Data
    @Builder
    public static class MemberDTO {
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




