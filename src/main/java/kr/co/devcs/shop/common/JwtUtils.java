package kr.co.devcs.shop.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.devcs.shop.dto.MemberForm;
import kr.co.devcs.shop.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final MemberDetailsService memberDetailsService;
    private static final String JWT_SECRET_KEY = "secret_key";
    public String generateToken(Member member) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(member))
//                .setSubject(member.getEmail())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setExpiration(createExpiredDate());
        return jwtBuilder.compact();
    }
    public Boolean validateToken(String token) {
        Claims claims = getClaims(token);
        var exp = claims.getExpiration();
        return exp.after(new Date());
    }
    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public String parseTokenToUserInfo(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Authentication getAuthentication(String email) {
        MemberDetails memberDetails = memberDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, "", memberDetails.getAuthorities());
        return authentication;
    }
    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }
    private static Map<String, Object> createClaims(Member member) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", member.getEmail());
        claims.put("nickname", member.getNickname());
        return claims;
    }
    private static Date createExpiredDate() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 8);     // 8시간
        // c.add(Calendar.DATE, 1);         // 1일
        return c.getTime();
    }
}
