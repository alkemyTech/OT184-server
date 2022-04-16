package com.alkemy.ong.data.members.service.impl;

import com.alkemy.ong.data.members.entity.MembersEntity;
import com.alkemy.ong.data.members.mapper.EntityModel;
import com.alkemy.ong.data.members.repository.MembersRepository;
import com.alkemy.ong.data.members.service.MembersIEntity;
import com.alkemy.ong.domain.members.model.MembersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersIEntityImpl implements MembersIEntity {

    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private EntityModel entityModel;



    @Override
    public List<MembersModel> getAll() {
        List<MembersEntity> entity = membersRepository.findAll();
        List<MembersModel> model = entityModel.membersEntityToModel(entity);
        return model;
    }
}
