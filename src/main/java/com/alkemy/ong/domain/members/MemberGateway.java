package com.alkemy.ong.domain.members;

import com.alkemy.ong.domain.members.Members;

import java.util.List;

public interface MemberGateway {

    Members save(Members members);

    List<Members> getAll();

    void delete(Long id);
}
