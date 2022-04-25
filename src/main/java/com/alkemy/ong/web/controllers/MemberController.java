package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.members.MemberService;
import com.alkemy.ong.domain.members.Members;
import com.alkemy.ong.domain.utils.PageModel;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody MemberDTO memberDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                toMemberDTO(
                        memberService.save(
                                toMember(memberDTO))));
    }

    @GetMapping
    public ResponseEntity<PageResponse<MemberDTO>> findByPage(@Valid @RequestParam("page") int page){
        PageModel<Members> pageMember = memberService.findByPage(page);
        String path = "/members";
        PageResponse response = PageResponse.builder()
                .content(pageMember
                        .getContent()
                        .stream()
                        .map(this::toMemberDTO)
                        .collect(toList()))
                .build();
        response.setResponse(path,page,pageMember.getTotalPages(),pageMember.isFirst(),pageMember.isLast());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private Members toMember(MemberDTO memberDTO){
        return Members
                .builder().name(memberDTO.getName())
                .facebookUrl(memberDTO.getFacebookUrl()).instagramUrl(memberDTO.getInstagramUrl())
                .linkedinUrl(memberDTO.getLinkedinUrl()).image(memberDTO.getImage())
                .description(memberDTO.getDescription()).build();
    }

    private MemberDTO toMemberDTO(Members members){
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
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable Long id,@Valid @RequestBody MemberDTO memberDTO){
        return ResponseEntity.ok().body(toMemberDTO(memberService.update(id, toMember(memberDTO))));
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
