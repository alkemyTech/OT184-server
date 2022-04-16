package com.alkemy.ong.domain.members.service.impl;


import com.alkemy.ong.data.members.service.MembersIEntity;
import com.alkemy.ong.domain.members.model.MembersModel;
import com.alkemy.ong.domain.members.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


public class MembersServiceImpl implements MembersService {


    @Autowired
    private MembersIEntity membersIEntity;

    @Override
    public List<MembersModel> getAll() {
        List<MembersModel> members = membersIEntity.getAll();
        return members;
    }
}
