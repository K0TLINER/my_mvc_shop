package kr.co.devcs.shop.common;

import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.repository.MemberRepository;
import kr.co.devcs.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public MemberDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername() start");
        System.out.println(email);
        Member member = memberRepository.findByEmail(email).orElseThrow();
        return new MemberDetails(member);
    }
}
