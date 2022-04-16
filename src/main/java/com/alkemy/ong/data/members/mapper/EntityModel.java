package com.alkemy.ong.data.members.mapper;


import com.alkemy.ong.data.members.entity.MembersEntity;
import com.alkemy.ong.domain.members.model.MembersModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityModel {

    private ModelMapper mapper;

    @Autowired
    public EntityModel (ModelMapper mapper){
        this.mapper = mapper;
    }

    public List<MembersModel> membersEntityToModel(List<MembersEntity> entities){
        List<MembersModel> membersModel = entities
                .stream()
                .map(entity -> mapper.map(entity, MembersModel.class))
                .collect(Collectors.toList());
        return membersModel;
    }
}
