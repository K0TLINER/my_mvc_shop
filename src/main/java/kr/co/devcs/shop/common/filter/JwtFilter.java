package kr.co.devcs.shop.common.filter;

import kr.co.devcs.shop.common.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtFilter doFilterInternal execute");
        String authorization = request.getHeader("Authorization");
        if(authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String token;
        if(authorization.length() > "Bearer ".length()) {
            token = authorization.substring("Bearer ".length());
        } else {
            token = null;
        }
        if(token == null || token.trim().equals("")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token: " + token);
        if(jwtUtils.validateToken(token)) {
            String email = (String) jwtUtils.getClaims(token).get("email");
            Authentication authentication = jwtUtils.getAuthentication(email);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
