package com.alkemy.ong.web.members.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
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
