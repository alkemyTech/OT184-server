package com.alkemy.ong.data.service;

import com.alkemy.ong.domain.members.model.Members;

import java.util.List;

public interface MemberGateway {
    List<Members> getAll();
}
