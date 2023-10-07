package kr.co.devcs.shop.member;

import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Role;
import kr.co.devcs.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class MemberTestApp {
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private MemberRepository memberRepository;
    @Test
    public void testInsertMember() {
        Member member = Member.builder()
                .memberId(UUID.randomUUID())
                .birthDate(LocalDate.now()).phone("010-0000-1111").nickname("test")
                .email("test@test.com")
                .role(Role.ROLE_USER)
                .socialFlag(false)
                .password(passwordEncoder.encode("test"))
                .build();
        memberRepository.save(member);
        Member admin = Member.builder()
                .memberId(UUID.randomUUID())
                .birthDate(LocalDate.now()).phone("010-0000-3333").nickname("admin")
                .email("admin@test.com")
                .role(Role.ROLE_ADMIN)
                .socialFlag(false)
                .password(passwordEncoder.encode("admin"))
                .build();
        memberRepository.save(admin);
    }
}
