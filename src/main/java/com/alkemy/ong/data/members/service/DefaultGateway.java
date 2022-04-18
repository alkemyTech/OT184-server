package com.alkemy.ong.data.members.service;

import com.alkemy.ong.data.members.entity.MemberEntity;
import com.alkemy.ong.data.members.repository.MembersRepository;
import com.alkemy.ong.domain.members.model.Members;
import com.alkemy.ong.domain.members.service.Gateway;
import com.alkemy.ong.web.members.controller.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultGateway implements Gateway {

    private final MembersRepository membersRepository;

    @Autowired
    public DefaultGateway(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }


    @Override
    public Members save(Members members) {
    MemberEntity entity = membersRepository.save(
            MemberEntity
                .builder().name(members.getName()).facebookUrl(members.getFacebookUrl())
                .instagramUrl(members.getInstagramUrl()).linkedinUrl(members.getLinkedinUrl())
                .image(members.getImage()).description(members.getDescription()).isDeleted(false).build());

    return Members.builder().id(entity.getId()).name(entity.getName()).facebookUrl(entity.getFacebookUrl())
                .instagramUrl(entity.getInstagramUrl()).linkedinUrl(entity.getLinkedinUrl())
                .image(entity.getImage()).description(entity.getDescription()).build();
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

    @Override
    public void delete(Long id) {
        Optional<MemberEntity> entity =  membersRepository.findById(id);
        if(entity.isPresent()){
            membersRepository.deleteById(id);
        }else{
            throw new ParamNotFound("Id miembro no valido");
        }
    }
}
