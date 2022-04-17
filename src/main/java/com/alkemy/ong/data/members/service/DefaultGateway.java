package com.alkemy.ong.data.members.service;

import com.alkemy.ong.data.members.entity.MemberEntity;
import com.alkemy.ong.data.members.repository.MembersRepository;
import com.alkemy.ong.domain.members.model.Members;
import com.alkemy.ong.domain.members.service.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultGateway implements Gateway {

    private final MembersRepository membersRepository;

    @Autowired
    public DefaultGateway(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }


    @Override
    public List<Members> getAll() {
        List<MemberEntity> entity = membersRepository.findAll();

        List<Members> model = new ArrayList<>();
        entity.forEach((MemberEntity) ->{
            model.add(
                Members.builder()
                    .id(MemberEntity.getId())
                    .name(MemberEntity.getName())
                    .facebookUrl(MemberEntity.getFacebookUrl())
                    .instagramUrl(MemberEntity.getInstagramUrl())
                    .linkedinUrl(MemberEntity.getLinkedinUrl())
                    .image(MemberEntity.getImage())
                    .description(MemberEntity.getDescription())
                    .build()
            );
        });
        return model;
    }
}
