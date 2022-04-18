package com.alkemy.ong.data.service;

import com.alkemy.ong.data.entity.MemberEntity;
import com.alkemy.ong.data.repository.MembersRepository;
import com.alkemy.ong.domain.members.model.Members;
import com.alkemy.ong.domain.members.service.Gateway;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultMemberGateway implements MemberGateway {

    private final MembersRepository membersRepository;

    public DefaultMemberGateway(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    @Override
    public List<Members> getAll() {
        return membersRepository
            .findAll()
            .stream()
            .map(e -> toModel(e))
            .collect(Collectors.toList());
    }

    private Members toModel(MemberEntity entity){
        return Members
                .builder().id(entity.getId()).name(entity.getName())
                .facebookUrl(entity.getFacebookUrl()).instagramUrl(entity.getInstagramUrl())
                .linkedinUrl(entity.getLinkedinUrl()).image(entity.getImage())
                .description(entity.getDescription()).build();
    }
}
