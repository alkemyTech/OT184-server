package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.data.mapper.OrganizationModelMapper;
import com.alkemy.ong.data.repositories.OrganizationRepository;
import com.alkemy.ong.domain.organizations.Organization;
import com.alkemy.ong.domain.organizations.OrganizationGateway;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class DefaultOrganizationGateway implements OrganizationGateway {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationModelMapper organizationModelMapper;

    @Override
    public Organization findById(Long id){
        Optional<OrganizationEntity> organizationEntity= organizationRepository.findById(id);
        if(!organizationEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Organization organization= organizationModelMapper.entity2Model(organizationEntity.get());
        return organization;
    }


}
