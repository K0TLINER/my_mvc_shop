package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.MemberForm;
import kr.co.devcs.shop.dto.MemberSearchForm;
import kr.co.devcs.shop.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface MemberService {
    String login(String email, String password);
    Member addMember(MemberForm memberForm);
    Member updateMember(MemberForm memberForm);
    void deleteMember(Member member);
    Member getMemberByEmail(String email);
    List<Member> getMemberList(MemberSearchForm memberSearchForm);
    Boolean existByEmail(String email);
    Boolean existByNickname(String nickname);
}
