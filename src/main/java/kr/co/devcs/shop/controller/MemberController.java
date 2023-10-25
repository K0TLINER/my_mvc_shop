package kr.co.devcs.shop.controller;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.MemberForm;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(
            @RequestBody MemberForm memberForm
            ) {
        String token = memberService.login(memberForm.getEmail(), memberForm.getPassword());
        return ResponseEntity.ok().body(Map.of("token", token));
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(
            @Valid @RequestBody MemberForm memberForm
        ) {
        memberService.addMember(memberForm);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> update(
            @CurrentMember Member currentMember,
            MemberForm memberForm
        ) throws Exception {
        if(currentMember.getMemberId() != memberForm.getMemberId()) throw new AccessDeniedException("수정 권한이 없습니다.");
        memberService.updateMember(memberForm);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> delete(
            @CurrentMember Member currentMember,
            @PathVariable("email") String email
    ) throws Exception {
        Member member = memberService.getMemberByEmail(email);
        if(currentMember.getMemberId() != member.getMemberId()) throw new AccessDeniedException("삭제 권한이 없습니다.");
        memberService.deleteMember(member);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/get/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> get(
            @PathVariable("email") String email
    ) {
        Member member = memberService.getMemberByEmail(email);
        return ResponseEntity.ok().body(member);
    }
    @RequestMapping(value = "/exist/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> existByEmail(
            @PathVariable("email") String email
    ) {
        boolean value = memberService.existByEmail(email);
        return ResponseEntity.ok().body(Map.of("value", value));
    }
    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> checkToken(
            @CurrentMember Member currentMember
    ) {
        return ResponseEntity.ok().body(currentMember);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok().build();
    }
}
