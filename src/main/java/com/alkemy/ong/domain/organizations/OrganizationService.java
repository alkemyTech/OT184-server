package com.alkemy.ong.domain.organizations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
     private  OrganizationGateway organizationGateway;

    public Organization findById(Long id){

        return organizationGateway.findById(id);
    }
}
