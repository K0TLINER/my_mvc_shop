package kr.co.devcs.shop.entity;

import lombok.*;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");
    String role;
}
