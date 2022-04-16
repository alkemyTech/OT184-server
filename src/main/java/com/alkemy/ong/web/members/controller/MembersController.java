package com.alkemy.ong.web.members.controller;

import com.alkemy.ong.data.members.service.MembersIEntity;
import com.alkemy.ong.domain.members.model.MembersModel;
import com.alkemy.ong.web.members.dto.MembersDTO;
import com.alkemy.ong.web.members.mapper.MembersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("members")
public class MembersController {


    private MembersIEntity membersIEntity;
    private MembersMapper membersMapper;

    @Autowired
    public MembersController (MembersIEntity membersIEntity, MembersMapper membersMapper){
        this.membersMapper = membersMapper;
        this.membersIEntity = membersIEntity;
    }

    @GetMapping
    public ResponseEntity<List<MembersDTO>> getAll(){
        List<MembersModel> membersModels = membersIEntity.getAll();
        List<MembersDTO> membersDTOS = membersMapper.membersModelToDTO(membersModels);
        return ResponseEntity.ok().body(membersDTOS);
    }



}
