package com.alkemy.ong.domain.members.service;

import com.alkemy.ong.data.service.DefaultMemberGateway;
import com.alkemy.ong.domain.members.model.Members;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements Gateway {

    private DefaultMemberGateway defaultMemberGateway;

    public  MemberService (DefaultMemberGateway defaultMemberGateway){
        this.defaultMemberGateway = defaultMemberGateway;
    }

    @Override
    public List<Members> getAll() {
        return defaultMemberGateway.getAll();
    }
}
