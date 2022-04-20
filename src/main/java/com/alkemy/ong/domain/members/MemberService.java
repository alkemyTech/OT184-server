package com.alkemy.ong.domain.members;

import com.alkemy.ong.domain.categories.Category;
import com.alkemy.ong.domain.categories.CategoryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberGateway memberGateway;

    public MemberService(MemberGateway memberGateway){
        this.memberGateway = memberGateway;
    }

    public Members save(Members members){
        return memberGateway.save(members);
    };

    public List<Members> getAll(){
        return memberGateway.getAll();
    };

    public void delete(Long id){
        memberGateway.delete(id);
    };
}
