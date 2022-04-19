package com.alkemy.ong.domain.organizations;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Organization {

    private String name;
    private String image;
    private String address;
    private Integer phone;
    private String facebook;
    private String linkedin;
    private String instagram;
}
