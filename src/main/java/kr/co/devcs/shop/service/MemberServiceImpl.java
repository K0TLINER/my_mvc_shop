package kr.co.devcs.shop.service;

import kr.co.devcs.shop.common.JwtUtils;
import kr.co.devcs.shop.dto.MemberForm;
import kr.co.devcs.shop.dto.MemberSearchForm;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Role;
import kr.co.devcs.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;
    @Override
    public String login(String email, String password) {
        Member member = memberRepository.findByEmailAndSocialFlag(email, false).orElseThrow();
        if(!password.equals(member.getPassword()))  throw new IllegalArgumentException("Password cannot be null or empty.");
//        if(!passwordEncoder.matches(password, member.getPassword()))  throw new IllegalArgumentException("Password cannot be null or empty.");
        return jwtUtils.generateToken(member);
    }

    @Override
    public Member addMember(MemberForm memberForm) {
        Member member = Member.builder()
                .memberId(UUID.randomUUID())
                .email(memberForm.getEmail())
                .nickname(memberForm.getNickname())
                .phone(memberForm.getPhone())
                .birthDate(memberForm.getBirthDate())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .role(Role.ROLE_USER)
                .socialFlag(false)
                .active(true)
                .build();
        System.out.println("point");
        Member newMember = memberRepository.save(member);
        if(newMember == null) throw new IllegalArgumentException();
        return member;
    }

    @Override
    public Member updateMember(MemberForm memberForm) {
        Member member = memberRepository.findById(memberForm.getMemberId()).orElseThrow();
        member.setNickname(memberForm.getNickname());
        member.setBirthDate(memberForm.getBirthDate());
        member.setPhone(memberForm.getPhone());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();
        return member;
    }
    @Override
    public List<Member> getMemberList(MemberSearchForm memberSearchForm) {
        return null;
    }

    @Override
    public Boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public Boolean existByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}