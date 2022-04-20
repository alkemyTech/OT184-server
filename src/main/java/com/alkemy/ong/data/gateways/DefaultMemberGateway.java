package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.MemberEntity;
import com.alkemy.ong.data.repositories.MembersRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.members.MemberGateway;
import com.alkemy.ong.domain.members.Members;
import com.alkemy.ong.web.controllers.ParamNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class DefaultMemberGateway implements MemberGateway {

    private final MembersRepository membersRepository;

    public DefaultMemberGateway(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    @Override
    public Members save(Members members) {
        return toModel(membersRepository.save(toEntity(members)));
    }

    @Override
    public List<Members> getAll() {
        return membersRepository.findAll()
                        .stream()
                        .map(e -> toModel(e))
                        .collect(toList());
    }

    @Override
    public void delete(Long id) {
        membersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Id"));
        membersRepository.deleteById(id);
    }

    private MemberEntity toEntity (Members members){
        return MemberEntity
                .builder().name(members.getName()).facebookUrl(members.getFacebookUrl())
                .instagramUrl(members.getInstagramUrl()).linkedinUrl(members.getLinkedinUrl())
                .image(members.getImage()).description(members.getDescription()).isDeleted(false).build();
    }

    private Members toModel(MemberEntity memberEntity){
        return Members.builder()
                .id(memberEntity.getId()).name(memberEntity.getName())
                .facebookUrl(memberEntity.getFacebookUrl()).instagramUrl(memberEntity.getInstagramUrl())
                .linkedinUrl(memberEntity.getLinkedinUrl()).image(memberEntity.getImage())
                .description(memberEntity.getDescription()).build();
    }

}
