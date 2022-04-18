package com.alkemy.ong.data.members.repository;

import com.alkemy.ong.data.members.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<MemberEntity, Long> {
}
