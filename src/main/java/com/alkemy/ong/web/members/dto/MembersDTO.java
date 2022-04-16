package com.alkemy.ong.web.members.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class MembersDTO {
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
