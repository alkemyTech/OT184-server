package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.MemberEntity;
import com.alkemy.ong.data.repositories.MembersRepository;
import com.alkemy.ong.domain.members.MemberGateway;
import com.alkemy.ong.domain.members.Members;
import com.alkemy.ong.web.controllers.ParamNotFound;
import org.springframework.stereotype.Component;

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
        return membersRepository.findAll()
                        .stream()
                        .map(e -> toModel(e))
                        .collect(toList());
    }

    public Members toModel(MemberEntity memberEntity){
        return Members.builder()
                .id(memberEntity.getId()).name(memberEntity.getName())
                .facebookUrl(memberEntity.getFacebookUrl()).instagramUrl(memberEntity.getInstagramUrl())
                .linkedinUrl(memberEntity.getLinkedinUrl()).image(memberEntity.getImage())
                .description(memberEntity.getDescription()).build();
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
