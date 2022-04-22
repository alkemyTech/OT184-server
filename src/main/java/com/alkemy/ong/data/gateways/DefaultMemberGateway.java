package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.MemberEntity;
import com.alkemy.ong.data.repositories.MembersRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.members.MemberGateway;
import com.alkemy.ong.domain.members.Members;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Members update(Long id, Members members) {
        MemberEntity memberEntity = membersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id"));

        return toModel(membersRepository.save(setMember(memberEntity, members)));
    }

    private MemberEntity setMember(MemberEntity memberEntity, Members members){
        memberEntity.setName(members.getName());
        memberEntity.setFacebookUrl(members.getFacebookUrl());
        memberEntity.setInstagramUrl(members.getInstagramUrl());
        memberEntity.setLinkedinUrl(members.getLinkedinUrl());
        memberEntity.setImage(members.getImage());
        memberEntity.setDescription(members.getDescription());

        return memberEntity;
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
