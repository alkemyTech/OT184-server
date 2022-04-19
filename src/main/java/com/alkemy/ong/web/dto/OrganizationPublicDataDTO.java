package com.alkemy.ong.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrganizationPublicDataDTO {

    private String name;
    private String image;
    private String address;
    private Integer phone;
}
