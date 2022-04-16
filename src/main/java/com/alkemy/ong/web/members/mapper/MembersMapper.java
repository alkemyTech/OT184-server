package com.alkemy.ong.web.members.mapper;


import com.alkemy.ong.domain.members.model.MembersModel;
import com.alkemy.ong.web.members.dto.MembersDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembersMapper {

    private ModelMapper mapper;

    @Autowired
    public MembersMapper (ModelMapper mapper){
        this.mapper = mapper;
    }

    public MembersModel membersDtoToModel(MembersDTO dto){
        MembersModel membersModel = mapper.map(dto, MembersModel.class);
        return membersModel;
    }

    public List<MembersDTO> membersModelToDTO(List<MembersModel> models){
        List<MembersDTO> membersDTO = models
                .stream()
                .map(model -> mapper.map(model, MembersDTO.class))
                .collect(Collectors.toList());
        return membersDTO;
    }
}
