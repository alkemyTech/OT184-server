package com.alkemy.ong.domain.members.service;

import com.alkemy.ong.domain.members.model.Members;

import java.util.List;

public interface Gateway {

    Members save(Members members);

    List<Members> getAll();

    void delete(Long id);
}
